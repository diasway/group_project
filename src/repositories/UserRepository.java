package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
            try (Connection connection = db.getConnection()) {

                String sql = "INSERT INTO users(user_name, user_age, user_gender, genre_id, password) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, user.getUser_name());
                st.setInt(2, user.getUser_age());
                st.setBoolean(3, user.getUser_gender());
                st.setInt(4, user.getGenre_id());
                st.setString(5, user.getPassword());

                return st.executeUpdate() > 0;
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
            }
            return false;
        }

    @Override
    public User getUserByName(String name) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT u.user_id, u.user_name, u.user_age, u.user_gender, u.genre_id, u.password, g.genre_name " +
                    "FROM users u " +
                    "JOIN genres g ON u.genre_id = g.genre_id " +
                    "WHERE u.user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getInt("genre_id"),
                        rs.getString("password"));
                user.setGenre_name(rs.getString("genre_name"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT u.user_id, u.user_name, u.user_age, u.user_gender, u.genre_id, u.password, g.genre_name " +
                    "FROM users u " +
                    "JOIN genres g ON u.genre_id = g.genre_id " +
                    "WHERE u.user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getInt("genre_id"),
                        rs.getString("password"));
                user.setGenre_name(rs.getString("genre_name"));
                return user;
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    public boolean getUserPassword(String name, String password) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if (rs.next() && rs.getString("password").equals(password)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE users SET user_name = ?, user_age = ?, user_gender = ?, genre_id = ?, password = ? WHERE user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user.getUser_name());
            st.setInt(2, user.getUser_age());
            st.setBoolean(3, user.getUser_gender());
            st.setInt(4, user.getGenre_id());
            st.setString(5, user.getPassword());
            st.setInt(6, user.getUser_id());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getUsersOlderThan18() {
            List<User> users = new ArrayList<>();
            try (Connection connection = db.getConnection()) {
                String sql = "SELECT * FROM users";
                PreparedStatement st = connection.prepareStatement(sql);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    if (rs.getInt("user_age") > 18) {
                        users.add(new User(
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getInt("user_age"),
                                rs.getBoolean("user_gender"),
                                rs.getInt("genre_id"),
                                rs.getString("password")));
                    }
                }
            } catch (SQLException e) {
                System.out.println("SQL error: " + e.getMessage());
            }
            return users;
        }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT u.user_id, u.user_name, u.user_age, u.user_gender, u.genre_id, u.password, g.genre_name " +
                    "FROM users u " +
                    "JOIN genres g ON u.genre_id = g.genre_id";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getInt("genre_id"),
                        rs.getString("password"));
                user.setGenre_name(rs.getString("genre_name"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}