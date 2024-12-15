package dao;

import entity.Restaurant;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantDAO extends DBConnection {

    public void create(Restaurant restaurant) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO restaurant (name, address) VALUES (?, ?)")) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Restaurant> read() {
        List<Restaurant> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM restaurant");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = new Restaurant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")
                );
                list.add(restaurant);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Restaurant restaurant) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE restaurant SET name = ?, address = ? WHERE id = ?")) {

            ps.setString(1, restaurant.getName());
            ps.setString(2, restaurant.getAddress());
            ps.setInt(3, restaurant.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Restaurant sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE restaurant SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from restaurant where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public Restaurant findById(int id) {
        Restaurant restaurant = null;
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM restaurant WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                restaurant = new Restaurant(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return restaurant;
    }
}
