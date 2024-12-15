package dao;

import entity.Odeme;
import entity.Siparis;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdemeDAO extends DBConnection {

    public void create(Odeme odeme) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO odeme (tarih, amount, siparis_id) VALUES (?, ?, ?)")) {

            ps.setDate(1, new java.sql.Date(odeme.getTarih().getTime()));
            ps.setBigDecimal(2, odeme.getAmount());
            ps.setInt(3, odeme.getSiparis().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Odeme> read() {
        List<Odeme> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM odeme");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Odeme odeme = new Odeme(
                        rs.getInt("id"),
                        rs.getDate("tarih"),
                        rs.getBigDecimal("amount"),
                        new SiparisDAO().findById(rs.getInt("siparis_id"))
                );
                list.add(odeme);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Odeme odeme) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE odeme SET tarih = ?, amount = ?, siparis_id = ? WHERE id = ?")) {

            ps.setDate(1, new java.sql.Date(odeme.getTarih().getTime()));
            ps.setBigDecimal(2, odeme.getAmount());
            ps.setInt(3, odeme.getSiparis().getId());
            ps.setInt(4, odeme.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Odeme sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE odeme SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from odeme where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Odeme findById(int id) {
        Odeme odeme = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM odeme WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                odeme = new Odeme(
                        rs.getInt("id"),
                        rs.getDate("tarih"),
                        rs.getBigDecimal("amount"),
                        new SiparisDAO().findById(rs.getInt("siparis_id"))
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return odeme;
    }
}
