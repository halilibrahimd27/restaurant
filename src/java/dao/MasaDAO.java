package dao;

import entity.Masa;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MasaDAO extends DBConnection {

    // CREATE: Yeni bir masa kaydı ekler
    public void create(Masa masa) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Masa (numara, durum) VALUES (?, ?)")) {

            ps.setInt(1, masa.getNumara());
            ps.setString(2, masa.getDurum());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MasaDAO.create hatası: " + e.getMessage());
        }
    }

    // READ: Tüm masa kayıtlarını getirir
    public List<Masa> read() {
        List<Masa> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Masa");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Masa masa = new Masa(
                        rs.getInt("id"),
                        rs.getInt("numara"),
                        rs.getString("durum"),
                        rs.getInt("kapasite")
                );
                list.add(masa);
            }

        } catch (SQLException e) {
            System.out.println("MasaDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre masa getirir
    public Masa findById(int id) {
        Masa masa = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Masa WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                masa = new Masa(
                        rs.getInt("id"),
                        rs.getInt("numara"),
                        rs.getString("durum"),
                        rs.getInt("kapasite")
                );
            }

        } catch (SQLException e) {
            System.out.println("MasaDAO.findById hatası: " + e.getMessage());
        }
        return masa;
    }

    // UPDATE: Bir masa kaydını günceller
    public void update(Masa masa) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE Masa SET numara = ?, durum = ? WHERE id = ?")) {

            ps.setInt(1, masa.getNumara());
            ps.setString(2, masa.getDurum());
            ps.setInt(3, masa.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MasaDAO.update hatası: " + e.getMessage());
        }
    }

    // DELETE: Belirli bir masa kaydını siler
    public void delete(int id) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Masa WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MasaDAO.delete hatası: " + e.getMessage());
        }
    }
}
