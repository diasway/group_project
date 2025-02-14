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
            String sql = "INSERT INTO users (user_name, password, role) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, admin.getName());
            st.setString(2, admin.getPassword());
            st.setString(3, admin.getRole());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
    @Override
    public List<Administration> getAllAdministrators() {
        List<Administration> admins = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            if (connection == null) {
                throw new SQLException("Database connection is null!");
            }

            String sql = "SELECT user_id, user_name, role FROM users WHERE role = 'admin'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                admins.add(new Administration(
                        rs.getInt("user_id"),
                        rs.getString("user_name")));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return admins;
    }
    @Override
    public String getUserRoleByName(String name) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT role FROM users WHERE user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}
