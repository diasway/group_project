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
            String sql = "INSERT INTO users(user_name, user_age, user_gender, preferred_genre, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getUser_name());
            st.setInt(2, user.getUser_age());
            st.setBoolean(3, user.getUser_gender());
            st.setString(4, user.getPreferred_genre());
            st.setString(5, user.getPassword());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(String name) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getString("preferred_genre"),
                        rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    public boolean getUserPassword(String name, String password){
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE user_name = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            ResultSet rs = st.executeQuery();
            if(rs.next() && rs.getString("password").equals(password)){
                return true;
            }
        }catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT user_id, user_name, user_age, user_gender, preferred_genre, password FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(rs.getInt("user_id"),
                rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getString("preferred_genre"),
                        rs.getString("password"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}
