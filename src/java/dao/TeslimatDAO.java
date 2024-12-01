package dao;

import entity.Siparis;
import entity.Teslimat;
import entity.Restaurant;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeslimatDAO extends DBConnection {

    public void create(Teslimat teslimat) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Teslimat (address, durum, restaurant_id, siparis_id) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, teslimat.getAddress());
            ps.setString(2, teslimat.getDurum());
            ps.setInt(3, teslimat.getRestaurant().getId());
            ps.setInt(4, teslimat.getSiparis().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Teslimat> read() {
        List<Teslimat> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Teslimat");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Teslimat teslimat = new Teslimat(
                        rs.getInt("id"),
                        rs.getString("address"),
                        rs.getString("durum"),
                        new RestaurantDAO().findById(rs.getInt("restaurant_id")),
                        new SiparisDAO().findById(rs.getInt("siparis_id"))
                );
                list.add(teslimat);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Teslimat teslimat) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE Teslimat SET address = ?, durum = ?, restaurant_id = ?, siparis_id = ? WHERE id = ?")) {

            ps.setString(1, teslimat.getAddress());
            ps.setString(2, teslimat.getDurum());
            ps.setInt(3, teslimat.getRestaurant().getId());
            ps.setInt(4, teslimat.getSiparis().getId());
            ps.setInt(5, teslimat.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Teslimat WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
