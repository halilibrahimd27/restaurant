package dao;

import entity.Calisan;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalisanDAO extends DBConnection {
    public void create(Calisan calisan) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO calisan (name) VALUES (?)")) {

            ps.setString(1, calisan.getName());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("CalisanDAO.create hatası: " + e.getMessage());
        }
    }

    public List<Calisan> read() {
        List<Calisan> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM calisan");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Calisan calisan = new Calisan(
                        rs.getInt("id"),
                        rs.getString("name")
                );
                list.add(calisan);
            }

        } catch (SQLException e) {
            System.out.println("CalisanDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre çalışan getirir
    public Calisan findById(int id) {
        Calisan calisan = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM calisan WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                calisan = new Calisan(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }

        } catch (SQLException e) {
            System.out.println("CalisanDAO.findById hatası: " + e.getMessage());
        }
        return calisan;
    }

    // UPDATE: Çalışan kaydını günceller
    public void update(Calisan calisan) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE calisan SET name = ? WHERE id = ?")) {

            ps.setString(1, calisan.getName());
            ps.setInt(2, calisan.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("CalisanDAO.update hatası: " + e.getMessage());
        }
    }

    // DELETE: Bir çalışan kaydını siler
      public void delete(Calisan c) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE calisan SET id = id - 1 WHERE id > " + c.getId();
            String query1 = "DELETE from admin where id=" + c.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
