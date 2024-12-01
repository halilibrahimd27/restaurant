package dao;

import entity.Tatli;
import entity.Menu;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TatliDAO extends DBConnection {

    public void create(Tatli tatli) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Tatli (name, menu_id) VALUES (?, ?)")) {

            ps.setString(1, tatli.getName());
            ps.setInt(2, tatli.getMenu().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Tatli> read() {
        List<Tatli> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Tatli"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Tatli tatli = new Tatli(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new MenuDAO().findById(rs.getInt("menu_id"))
                );
                list.add(tatli);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(Tatli tatli) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE Tatli SET name = ?, menu_id = ? WHERE id = ?")) {

            ps.setString(1, tatli.getName());
            ps.setInt(2, tatli.getMenu().getId());
            ps.setInt(3, tatli.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("DELETE FROM Tatli WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Tatli findById(int id) {
        Tatli tatli = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Tatli WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Menu menu = null;
                int menuId = rs.getInt("menu_id");
                if (!rs.wasNull()) {
                    menu = new MenuDAO().findById(menuId);
                }

                tatli = new Tatli(
                        rs.getInt("id"),
                        rs.getString("name"),
                        menu
                );
            }

        } catch (SQLException e) {
            System.out.println("TatliDAO.findById hatası: " + e.getMessage());
        }
        return tatli;
    }

}
