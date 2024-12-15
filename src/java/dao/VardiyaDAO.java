package dao;

import entity.Calisan;
import entity.Vardiya;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VardiyaDAO extends DBConnection {

    // CREATE: Yeni bir vardiya kaydı ekler
    public void create(Vardiya vardiya) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Vardiya (saatler, calisan_id) VALUES (?, ?)")) {

            ps.setString(1, vardiya.getSaatler());
            ps.setInt(2, vardiya.getCalisan().getId()); // Çalışan ile ilişkilendirme
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("VardiyaDAO.create hatası: " + e.getMessage());
        }
    }

    // READ: Tüm vardiya kayıtlarını getirir
    public List<Vardiya> read() {
        List<Vardiya> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM vardiya");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Calisan calisan = null;
                int calisanId = rs.getInt("calisan_id");
                if (!rs.wasNull()) { // Çalışan ID null değilse Çalışan nesnesini getir
                    calisan = new CalisanDAO().findById(calisanId);
                }

                Vardiya vardiya = new Vardiya(
                        rs.getInt("id"),
                        rs.getString("saatler"),
                        calisan
                );
                list.add(vardiya);
            }

        } catch (SQLException e) {
            System.out.println("VardiyaDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre vardiya getirir
    public Vardiya findById(int id) {
        Vardiya vardiya = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM vardiya WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Calisan calisan = null;
                int calisanId = rs.getInt("calisan_id");
                if (!rs.wasNull()) {
                    calisan = new CalisanDAO().findById(calisanId);
                }

                vardiya = new Vardiya(
                        rs.getInt("id"),
                        rs.getString("saatler"),
                        calisan
                );
            }

        } catch (SQLException e) {
            System.out.println("VardiyaDAO.findById hatası: " + e.getMessage());
        }
        return vardiya;
    }

    // UPDATE: Bir vardiya kaydını günceller
    public void update(Vardiya vardiya) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE vardiya SET saatler = ?, calisan_id = ? WHERE id = ?")) {

            ps.setString(1, vardiya.getSaatler());
            ps.setInt(2, vardiya.getCalisan().getId());
            ps.setInt(3, vardiya.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("VardiyaDAO.update hatası: " + e.getMessage());
        }
    }

    public void delete(Vardiya sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE vardiya SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from vardiya where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
