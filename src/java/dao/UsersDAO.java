package dao;

import entity.Users;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO extends DBConnection {

    // CREATE: Yeni bir kullanıcı ekler
    public void create(Users user) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (userName, adress, password) VALUES (?, ?, ?)")) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getAdress());
            ps.setString(3, user.getPassword());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("UsersDAO.create hatası: " + e.getMessage());
        }
    }

    // READ: Tüm kullanıcıları getirir
    public List<Users> read() {
        List<Users> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Users user = new Users(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("adress"),
                        rs.getString("password")
                );
                list.add(user);
            }

        } catch (SQLException e) {
            System.out.println("UsersDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre kullanıcı getirir
    public Users findById(int id) {
        Users user = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new Users(
                        rs.getInt("id"),
                        rs.getString("userName"),
                        rs.getString("adress"),
                        rs.getString("password")
                );
            }

        } catch (SQLException e) {
            System.out.println("UsersDAO.findById hatası: " + e.getMessage());
        }
        return user;
    }

    // UPDATE: Kullanıcı bilgilerini günceller
    public void update(Users user) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE Users SET userName = ?, adress = ?, password = ? WHERE id = ?")) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getAdress());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("UsersDAO.update hatası: " + e.getMessage());
        }
    }

    // DELETE: Belirli bir kullanıcıyı siler
    public void delete(int id) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Users WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("UsersDAO.delete hatası: " + e.getMessage());
        }
    }
}
