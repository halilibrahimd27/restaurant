package dao;

import entity.Iletisim;
import entity.Users;
import entity.Restaurant;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IletisimDAO extends DBConnection {

    public void create(Iletisim iletisim) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO iletisim (user_id, restaurant_id, mesaj) VALUES (?, ?, ?)")) {

            ps.setInt(1, iletisim.getUser().getId());
            ps.setInt(2, iletisim.getRestaurant().getId());
            ps.setString(3, iletisim.getMesaj());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Iletisim> read() {
        List<Iletisim> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM iletisim");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Iletisim iletisim = new Iletisim(
                        rs.getInt("id"),
                        new UsersDAO().findById(rs.getInt("user_id")),
                        new RestaurantDAO().findById(rs.getInt("restaurant_id")),
                        rs.getString("mesaj")
                );
                list.add(iletisim);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Iletisim iletisim) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE iletisim SET user_id = ?, restaurant_id = ?, mesaj = ? WHERE id = ?")) {

            ps.setInt(1, iletisim.getUser().getId());
            ps.setInt(2, iletisim.getRestaurant().getId());
            ps.setString(3, iletisim.getMesaj());
            ps.setInt(4, iletisim.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(Iletisim sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE iletisim SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from iletisim where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
