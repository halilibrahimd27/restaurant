package dao;

import entity.Restaurant;
import entity.Tedarikci;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TedarikciDAO extends DBConnection {

    public void create(Tedarikci tedarikci) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Tedarikci (name, phone, restaurant_id) VALUES (?, ?, ?)")) {

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
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Tedarikci"); ResultSet rs = ps.executeQuery()) {

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
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE Tedarikci SET name = ?, phone = ?, restaurant_id = ? WHERE id = ?")) {

            ps.setString(1, tedarikci.getName());
            ps.setString(2, tedarikci.getPhone());
            ps.setInt(3, tedarikci.getRestaurant().getId());
            ps.setInt(4, tedarikci.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("DELETE FROM Tedarikci WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Tedarikci findById(int id) {
        Tedarikci tedarikci = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Tedarikci WHERE id = ?")) {

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
