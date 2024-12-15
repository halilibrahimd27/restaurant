package dao;

import entity.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class UsersDAO extends DBConnection {
    
    public int newUserId;

    public boolean isValidUser(String name, String password) {

        boolean isValid = false;
        jakarta.resource.cci.Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
            statement = this.getConnect().prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, password);

            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newUserId = resultSet.getInt("id");
                isValid = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }


    public List<Users> read() {

        List<Users> userList = new ArrayList<>();

        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query = "select * from users ";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                userList.add(new Users(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("address"), rs.getString("phone")));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public Users findById(int id) {
        Users user = null;
        try (java.sql.Connection conn = this.getConnect(); 
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Users(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            System.out.println("UsersDAO.findById hatası: " + e.getMessage());
        }
        return user;
    }

    public void create(Users u) {

        try {

            Statement st = (Statement) this.getConnect().createStatement();
            st.executeUpdate("INSERT INTO users(username,password,address,phone) VALUES ('"
                    + u.getUserName() + "','"
                    + u.getPassword() + "','"
                    + u.getAddress() + "','"
                    + u.getPhone() + "')");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Users u) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE users SET id = id - 1 WHERE id > " + u.getId();
            String query1 = "DELETE from users where id=" + u.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(Users u) {
        try {
            Statement st = this.getConnect().createStatement();
            String query = "UPDATE users SET "
                    + "username = '" + u.getUserName() + "', "
                    + "password = '" + u.getPassword() + "', "
                    + "address = '" + u.getAddress() + "', "
                    + "phone = '" + u.getPhone() + "', "
                    + "WHERE id = " + u.getId();
            st.executeUpdate(query);
            System.out.println("Users başarıyla güncellendi.");
        } catch (SQLException e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

}
