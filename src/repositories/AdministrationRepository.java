package repositories;

import data.interfaces.IDB;
import models.Administration;
import repositories.interfaces.IAdministrationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministrationRepository implements IAdministrationRepository {
    private final IDB db;

    public AdministrationRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createAdministration(Administration admin) {
        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }
            String sql = "INSERT INTO administration (name, account_password) VALUES (?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, admin.getName());
            st.setString(2, admin.getPassword());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Administration getAdministrationById(int id) {
        if (id <= 0) {
            System.out.println("Invalid ID: ID must be greater than zero.");
            return null;
        }

        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }

            String sql = "SELECT id, name, account_password FROM administration WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Administration(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("account_password")
                );
            } else {
                System.out.println("Administrator with ID " + id + " not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Administration> getAllAdministrators() {
        List<Administration> admins = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }

            String sql = "SELECT id, name, account_password FROM administration";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                admins.add(new Administration(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("account_password")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return admins;
    }

    @Override
    public void viewUserById(int userId) {
        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }

            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                System.out.printf("User Details:\n- ID: %d\n- Name: %s\n- Age: %d\n- Gender: %s\n- Genre ID: %d\n- Genre Name: %s\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        (rs.getBoolean("gender") ? "Male" : "Female"),
                        rs.getInt("genre_id"),
                        rs.getString("genre_name"));
            } else {
                System.out.println("User not found.");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteUser(int userId) {
        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }

            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }

            String sql = "SELECT id, name, age, gender FROM users";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                users.add(String.format("ID: %d | Name: %s | Age: %d | Gender: %s",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getBoolean("gender") ? "Male" : "Female"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return users;
    }
}
