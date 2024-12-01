package dao;

import entity.Menu;
import entity.Yemek;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class YemekDAO extends DBConnection {

    public void create(Yemek yemek) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Yemek (name, menu_id) VALUES (?, ?)")) {

            ps.setString(1, yemek.getName());
            ps.setInt(2, yemek.getMenu().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("YemekDAO.create hatası: " + e.getMessage());
        }
    }

    public List<Yemek> read() {
        List<Yemek> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Yemek"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Menu menu = new MenuDAO().findById(rs.getInt("menu_id"));
                Yemek yemek = new Yemek(
                        rs.getInt("id"),
                        rs.getString("name"),
                        menu
                );
                list.add(yemek);
            }

        } catch (SQLException e) {
            System.out.println("YemekDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    public void update(Yemek yemek) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE Yemek SET name = ?, menu_id = ? WHERE id = ?")) {

            ps.setString(1, yemek.getName());
            ps.setInt(2, yemek.getMenu().getId());
            ps.setInt(3, yemek.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("YemekDAO.update hatası: " + e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("DELETE FROM Yemek WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("YemekDAO.delete hatası: " + e.getMessage());
        }
    }

    public Yemek findById(int id) {
        Yemek yemek = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Yemek WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Menu menu = new MenuDAO().findById(rs.getInt("menu_id")); // Menu ile ilişki
                yemek = new Yemek(
                        rs.getInt("id"),
                        rs.getString("name"),
                        menu
                );
            }

        } catch (SQLException e) {
            System.out.println("YemekDAO.findById hatası: " + e.getMessage());
        }
        return yemek;
    }

}
