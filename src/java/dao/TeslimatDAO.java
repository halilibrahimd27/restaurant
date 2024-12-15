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
             PreparedStatement ps = conn.prepareStatement("INSERT INTO teslimat (address, durum, restaurant_id, siparis_id) VALUES (?, ?, ?, ?)")) {

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
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM teslimat");
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
             PreparedStatement ps = conn.prepareStatement("UPDATE teslimat SET address = ?, durum = ?, restaurant_id = ?, siparis_id = ? WHERE id = ?")) {

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

    public void delete(Teslimat sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE teslimat SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from teslimat where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
