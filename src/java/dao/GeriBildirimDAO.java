package dao;

import entity.GeriBildirim;
import entity.Users;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeriBildirimDAO extends DBConnection {

    public void create(GeriBildirim geriBildirim) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO geribildirim (yorum, user_id) VALUES (?, ?)")) {

            ps.setString(1, geriBildirim.getYorum());
            ps.setInt(2, geriBildirim.getUser().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<GeriBildirim> read() {
        List<GeriBildirim> list = new ArrayList<>();
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM geribildirim");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GeriBildirim geriBildirim = new GeriBildirim(
                        rs.getInt("id"),
                        rs.getString("yorum"),
                        new UsersDAO().findById(rs.getInt("user_id"))
                );
                list.add(geriBildirim);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public void update(GeriBildirim geriBildirim) {
        try (Connection conn = this.getConnect();
             PreparedStatement ps = conn.prepareStatement("UPDATE geribildirim SET yorum = ?, user_id = ? WHERE id = ?")) {

            ps.setString(1, geriBildirim.getYorum());
            ps.setInt(2, geriBildirim.getUser().getId());
            ps.setInt(3, geriBildirim.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(GeriBildirim sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE geribildirim SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from geribildirim where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
