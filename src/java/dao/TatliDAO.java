package dao;

import entity.Tatli;
import entity.Menu;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TatliDAO extends DBConnection {

    public void create(Tatli tatli) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO tatli(name, menu_id) VALUES (?, ?)")) {

            ps.setString(1, tatli.getName());
            ps.setInt(2, tatli.getMenu().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Tatli> read() {
        List<Tatli> list = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM tatli"); ResultSet rs = ps.executeQuery()) {

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
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE tatli SET name = ?, menu_id = ? WHERE id = ?")) {

            ps.setString(1, tatli.getName());
            ps.setInt(2, tatli.getMenu().getId());
            ps.setInt(3, tatli.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Tatli sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE tatli SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from tatli where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
