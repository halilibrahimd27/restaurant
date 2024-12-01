package dao;

import entity.Menu;
import entity.Restaurant;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO extends DBConnection {

    public void create(Menu menu) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Menu (name, restaurant_id) VALUES (?, ?)")) {

            ps.setString(1, menu.getName());
            ps.setInt(2, menu.getRestaurant().getId()); // restaurant_id ilişkilendirildi
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MenuDAO.create hatası: " + e.getMessage());
        }
    }

    public List<Menu> read() {
        List<Menu> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Menu"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Restaurant restaurant = null;
                int restaurantId = rs.getInt("restaurant_id");
                if (!rs.wasNull()) { // Eğer restaurant_id NULL değilse, ilgili restaurant'ı getir
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
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Menu WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                menu = new Menu(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new RestaurantDAO().findById(rs.getInt("restaurant_id")) // Restaurant ilişkisi
                );
            }

        } catch (SQLException e) {
            System.out.println("MenuDAO.findById hatası: " + e.getMessage());
        }
        return menu;
    }

    public void update(Menu menu) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE Menu SET name = ?, restaurant_id = ? WHERE id = ?")) {

            ps.setString(1, menu.getName());
            ps.setInt(2, menu.getRestaurant().getId()); // restaurant_id güncelleniyor
            ps.setInt(3, menu.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MenuDAO.update hatası: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("DELETE FROM Menu WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("MenuDAO.delete hatası: " + e.getMessage());
        }
    }
}
