package dao;

import entity.Calisan;
import entity.Garson;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GarsonDAO extends DBConnection {

    // CREATE: Yeni bir garson kaydı ekler
    public void create(Garson garson) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Garson (name, calisan_id) VALUES (?, ?)")) {

            ps.setString(1, garson.getName());
            ps.setInt(2, garson.getCalisan().getId()); // Çalışan ile ilişkilendirme
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("GarsonDAO.create hatası: " + e.getMessage());
        }
    }

    // READ: Tüm garson kayıtlarını getirir
    public List<Garson> read() {
        List<Garson> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Garson");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Calisan calisan = null;
                int calisanId = rs.getInt("calisan_id");
                if (!rs.wasNull()) { // Çalışan ID null değilse Çalışan nesnesini getir
                    calisan = new CalisanDAO().findById(calisanId);
                }

                Garson garson = new Garson(
                        rs.getInt("id"),
                        rs.getString("name"),
                        calisan
                );
                list.add(garson);
            }

        } catch (SQLException e) {
            System.out.println("GarsonDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre garson getirir
    public Garson findById(int id) {
        Garson garson = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Garson WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Calisan calisan = null;
                int calisanId = rs.getInt("calisan_id");
                if (!rs.wasNull()) {
                    calisan = new CalisanDAO().findById(calisanId);
                }

                garson = new Garson(
                        rs.getInt("id"),
                        rs.getString("name"),
                        calisan
                );
            }

        } catch (SQLException e) {
            System.out.println("GarsonDAO.findById hatası: " + e.getMessage());
        }
        return garson;
    }

    // UPDATE: Bir garson kaydını günceller
    public void update(Garson garson) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE Garson SET name = ?, calisan_id = ? WHERE id = ?")) {

            ps.setString(1, garson.getName());
            ps.setInt(2, garson.getCalisan().getId());
            ps.setInt(3, garson.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("GarsonDAO.update hatası: " + e.getMessage());
        }
    }

    // DELETE: Bir garson kaydını siler
    public void delete(int id) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Garson WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("GarsonDAO.delete hatası: " + e.getMessage());
        }
    }
}
