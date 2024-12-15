package dao;

import entity.Menu;
import entity.Restaurant;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends DBConnection {

    public void create(Menu menu) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO menu (name, restaurant_id) VALUES (?, ?)")) {

            ps.setString(1, menu.getName());
            ps.setInt(2, menu.getRestaurant().getId()); // restaurant_id ilişkilendirildi
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MenuDAO.create hatası: " + e.getMessage());
        }
    }

    public List<Menu> read() {
        List<Menu> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM menu"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = null;
                int restaurantId = rs.getInt("restaurant_id");
                if (!rs.wasNull()) {
                    restaurant = new RestaurantDAO().findById(restaurantId);
                }

                Menu menu = new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        restaurant
                );
                list.add(menu);
            }

        } catch (SQLException e) {
            System.out.println("MenuDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    public Menu findById(int id) {
        Menu menu = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM menu WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Restaurant rst = null;
                if (!rs.wasNull()) {
                    rst = new RestaurantDAO().findById(rs.getInt("restaurant_id"));
                }

                menu = new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rst
                );
            }

        } catch (SQLException e) {
            System.out.println("MenuDAO.findById hatası: " + e.getMessage());
        }
        return menu;
    }

    public void update(Menu menu) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE menu SET name = ?, restaurant_id = ? WHERE id = ?")) {

            ps.setString(1, menu.getName());
            ps.setInt(2, menu.getRestaurant().getId()); // restaurant_id güncelleniyor
            ps.setInt(3, menu.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MenuDAO.update hatası: " + e.getMessage());
        }
    }

    public void delete(Menu sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE menu SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from menu where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
