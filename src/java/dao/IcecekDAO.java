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
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO icecek (name, menu_id) VALUES (?, ?)")) {

            ps.setString(1, icecek.getName());
            ps.setInt(2, icecek.getMenu().getId()); // menu_id ile ilişkilendirme
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IcecekDAO.create hatası: " + e.getMessage());
        }
    }

    public List<Icecek> read() {
        List<Icecek> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM icecek"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Icecek icecek = new Icecek(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new MenuDAO().findById(rs.getInt("menu_id"))
                );
                list.add(icecek);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    // FIND BY ID: Belirli bir ID'ye göre içecek getirir
    public Icecek findById(int id) {
        Icecek icecek = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM icecek WHERE id = ?")) {

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
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE icecek SET name = ?, menu_id = ? WHERE id = ?")) {

            ps.setString(1, icecek.getName());
            ps.setInt(2, icecek.getMenu().getId());
            ps.setInt(3, icecek.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("IcecekDAO.update hatası: " + e.getMessage());
        }
    }

    public void delete(Icecek sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE icecek SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from icecek where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
