package dao;

import entity.Masa;
import entity.Rezervasyon;
import entity.Users;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RezervasyonDAO extends DBConnection {

    public void create(Rezervasyon rezervasyon) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO rezervasyon (tarih, saat, user_id, masa_id) VALUES (?, ?, ?, ?)")) {

            ps.setDate(1, new java.sql.Date(rezervasyon.getTarih().getTime()));
            ps.setString(2, rezervasyon.getSaat());
            ps.setInt(3, rezervasyon.getUser().getId());
            ps.setInt(4, rezervasyon.getMasa().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Rezervasyon> read() {
        List<Rezervasyon> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM rezervasyon");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Rezervasyon rezervasyon = new Rezervasyon(
                        rs.getInt("id"),
                        rs.getDate("tarih"),
                        rs.getString("saat"),
                        new UsersDAO().findById(rs.getInt("user_id")),
                        new MasaDAO().findById(rs.getInt("masa_id"))
                );
                list.add(rezervasyon);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Rezervasyon rezervasyon) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE rezervasyon SET tarih = ?, saat = ?, user_id = ?, masa_id = ? WHERE id = ?")) {

            ps.setDate(1, new java.sql.Date(rezervasyon.getTarih().getTime()));
            ps.setString(2, rezervasyon.getSaat());
            ps.setInt(3, rezervasyon.getUser().getId());
            ps.setInt(4, rezervasyon.getMasa().getId());
            ps.setInt(5, rezervasyon.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Rezervasyon sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE rezervasyon SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from rezervasyon where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
