package dao;

import entity.Icecek;
import entity.Menu;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IcecekDAO extends DBConnection {

    // CREATE: Yeni bir içecek ekler
    public void create(Icecek icecek) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Icecek (name, menu_id) VALUES (?, ?)")) {

            ps.setString(1, icecek.getName());
            ps.setInt(2, icecek.getMenu().getId()); // menu_id ile ilişkilendirme
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IcecekDAO.create hatası: " + e.getMessage());
        }
    }

    // READ: Tüm içecekleri getirir
    public List<Icecek> read() {
        List<Icecek> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Icecek"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Menu menu = null;
                int menuId = rs.getInt("menu_id");
                if (!rs.wasNull()) {
                    menu = new MenuDAO().findById(menuId); // Menu nesnesini getir
                }

                Icecek icecek = new Icecek(
                        rs.getInt("id"),
                        rs.getString("name"),
                        menu
                );
                list.add(icecek);
            }

        } catch (SQLException e) {
            System.out.println("IcecekDAO.read hatası: " + e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre içecek getirir
    public Icecek findById(int id) {
        Icecek icecek = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Icecek WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Menu menu = null;
                int menuId = rs.getInt("menu_id");
                if (!rs.wasNull()) {
                    menu = new MenuDAO().findById(menuId);
                }

                icecek = new Icecek(
                        rs.getInt("id"),
                        rs.getString("name"),
                        menu
                );
            }

        } catch (SQLException e) {
            System.out.println("IcecekDAO.findById hatası: " + e.getMessage());
        }
        return icecek;
    }

    // UPDATE: Bir içecek kaydını günceller
    public void update(Icecek icecek) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE Icecek SET name = ?, menu_id = ? WHERE id = ?")) {

            ps.setString(1, icecek.getName());
            ps.setInt(2, icecek.getMenu().getId());
            ps.setInt(3, icecek.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IcecekDAO.update hatası: " + e.getMessage());
        }
    }

    // DELETE: Bir içecek kaydını siler
    public void delete(int id) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("DELETE FROM Icecek WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IcecekDAO.delete hatası: " + e.getMessage());
        }
    }
}
