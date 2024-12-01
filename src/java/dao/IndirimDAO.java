package dao;

import entity.Indirim;
import entity.Users;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IndirimDAO extends DBConnection {

    // CREATE: Yeni bir indirim kaydı ekler
    public void create(Indirim indirim) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Indirim (amount, kullanici_id) VALUES (?, ?)")) {

            ps.setBigDecimal(1, indirim.getAmount());
            ps.setInt(2, indirim.getUser().getId()); // Kullanıcıyla ilişkilendirme
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IndirimDAO.create hatası: " + e.getMessage());
        }
    }

    // READ: Tüm indirim kayıtlarını getirir
    public List<Indirim> read() {
        List<Indirim> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Indirim");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Users user = null;
                int userId = rs.getInt("kullanici_id");
                if (!rs.wasNull()) { // Kullanıcı ID null değilse Users nesnesini getir
                    user = new UsersDAO().findById(userId);
                }

                Indirim indirim = new Indirim(
                        rs.getInt("id"),
                        rs.getBigDecimal("amount"),
                        user
                );
                list.add(indirim);
            }

        } catch (SQLException e) {
            System.out.println("IndirimDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre indirim getirir
    public Indirim findById(int id) {
        Indirim indirim = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Indirim WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Users user = null;
                int userId = rs.getInt("kullanici_id");
                if (!rs.wasNull()) {
                    user = new UsersDAO().findById(userId);
                }

                indirim = new Indirim(
                        rs.getInt("id"),
                        rs.getBigDecimal("amount"),
                        user
                );
            }

        } catch (SQLException e) {
            System.out.println("IndirimDAO.findById hatası: " + e.getMessage());
        }
        return indirim;
    }

    // UPDATE: Bir indirim kaydını günceller
    public void update(Indirim indirim) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE Indirim SET amount = ?, kullanici_id = ? WHERE id = ?")) {

            ps.setBigDecimal(1, indirim.getAmount());
            ps.setInt(2, indirim.getUser().getId());
            ps.setInt(3, indirim.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IndirimDAO.update hatası: " + e.getMessage());
        }
    }

    // DELETE: Bir indirim kaydını siler
    public void delete(int id) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Indirim WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IndirimDAO.delete hatası: " + e.getMessage());
        }
    }
}
