package dao;

import entity.Restaurant;
import entity.Tedarikci;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TedarikciDAO extends DBConnection {

    public void create(Tedarikci tedarikci) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO tedarikci (name, phone, restaurant_id) VALUES (?, ?, ?)")) {

            ps.setString(1, tedarikci.getName());
            ps.setString(2, tedarikci.getPhone());
            ps.setInt(3, tedarikci.getRestaurant().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Tedarikci> read() {
        List<Tedarikci> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM tedarikci"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tedarikci tedarikci = new Tedarikci(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        new RestaurantDAO().findById(rs.getInt("restaurant_id"))
                );
                list.add(tedarikci);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Tedarikci tedarikci) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE tedarikci SET name = ?, phone = ?, restaurant_id = ? WHERE id = ?")) {

            ps.setString(1, tedarikci.getName());
            ps.setString(2, tedarikci.getPhone());
            ps.setInt(3, tedarikci.getRestaurant().getId());
            ps.setInt(4, tedarikci.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Tedarikci sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE tedarikci SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from tedarikci where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
    public Tedarikci findById(int id) {
        Tedarikci tedarikci = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM tedarikci WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Restaurant restaurant = null;
                int restaurantId = rs.getInt("restaurant_id");
                if (!rs.wasNull()) {
                    restaurant = new RestaurantDAO().findById(restaurantId);
                }

                tedarikci = new Tedarikci(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        restaurant
                );
            }

        } catch (SQLException e) {
            System.out.println("TedarikciDAO.findById hatası: " + e.getMessage());
        }
        return tedarikci;
    }

}
