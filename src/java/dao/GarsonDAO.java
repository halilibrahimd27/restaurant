package dao;

import entity.Calisan;
import entity.Garson;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GarsonDAO extends DBConnection {

    
    public void create(Garson garson) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO garson (name, calisan_id) VALUES (?, ?)")) {

            ps.setString(1, garson.getName());
            ps.setInt(2, garson.getCalisan().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("GarsonDAO.create hatası: " + e.getMessage());
        }
    }

    public List<Garson> read() {
        List<Garson> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM garson");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Calisan calisan = null;
                int calisanId = rs.getInt("calisan_id");
                if (!rs.wasNull()) { 
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

    public Garson findById(int id) {
        Garson garson = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM garson WHERE id = ?")) {

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

    public void update(Garson garson) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE garson SET name = ?, calisan_id = ? WHERE id = ?")) {

            ps.setString(1, garson.getName());
            ps.setInt(2, garson.getCalisan().getId());
            ps.setInt(3, garson.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("GarsonDAO.update hatası: " + e.getMessage());
        }
    }
    
    public void delete(Garson sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE garson SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from garson where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
