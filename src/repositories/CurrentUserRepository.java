package repositories;

import data.interfaces.IDB;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentUserRepository implements repositories.interfaces.ICurrentUserRepository {
    private final IDB db;

    public CurrentUserRepository(IDB db){
        this.db = db;
    }

    @Override
    public User getUserInfo(String name) {
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
                        rs.getInt("genre_id"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}
