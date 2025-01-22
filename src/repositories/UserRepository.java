package repositories;

import data.interfaces.IDB;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO users(user_name, user_age, user_gender, preferred_genre) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getUser_name());
            st.setInt(2, user.getUser_age());
            st.setBoolean(3, user.getUser_gender());
            st.setString(4, user.getPreferred_genre());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getBoolean("gender"),
                        rs.getString("preferred_genre"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT user_id, user_name, user_age, user_gender, preferred_genre FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getBoolean("gender"),
                        rs.getString("preferred_genre"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}
