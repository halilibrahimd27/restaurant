package dao;

import entity.Masa;
import entity.Siparis;
import entity.Users;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SiparisDAO extends DBConnection {

    public void create(Siparis siparis) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Siparis (tarih, amount, user_id, masa_id) VALUES (?, ?, ?, ?)")) {

            ps.setDate(1, new java.sql.Date(siparis.getTarih().getTime()));
            ps.setBigDecimal(2, siparis.getAmount());
            ps.setInt(3, siparis.getUser().getId());
            ps.setInt(4, siparis.getMasa().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Siparis> read() {
        List<Siparis> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Siparis");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Siparis siparis = new Siparis(
                        rs.getInt("id"),
                        rs.getDate("tarih"),
                        rs.getBigDecimal("amount"),
                        new UsersDAO().findById(rs.getInt("user_id")),
                        new MasaDAO().findById(rs.getInt("masa_id"))
                );
                list.add(siparis);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Siparis siparis) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE Siparis SET tarih = ?, amount = ?, user_id = ?, masa_id = ? WHERE id = ?")) {

            ps.setDate(1, new java.sql.Date(siparis.getTarih().getTime()));
            ps.setBigDecimal(2, siparis.getAmount());
            ps.setInt(3, siparis.getUser().getId());
            ps.setInt(4, siparis.getMasa().getId());
            ps.setInt(5, siparis.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Siparis WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Siparis findById(int id) {
        Siparis siparis = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Siparis WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                siparis = new Siparis(
                        rs.getInt("id"),
                        rs.getDate("tarih"),
                        rs.getBigDecimal("amount"),
                        new UsersDAO().findById(rs.getInt("user_id")),
                        new MasaDAO().findById(rs.getInt("masa_id"))
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return siparis;
    }
}
