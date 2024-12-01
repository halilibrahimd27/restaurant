package dao;

import entity.Stok;
import entity.Tatli;
import entity.Tedarikci;
import entity.Yemek;
import entity.Icecek;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StokDAO extends DBConnection {

    public void create(Stok stok) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("INSERT INTO Stok (miktar, yemek_id, icecek_id, tatli_id, tedarikci_id) VALUES (?, ?, ?, ?, ?)")) {

            ps.setInt(1, stok.getMiktar());
            ps.setInt(2, stok.getYemek().getId());
            ps.setInt(3, stok.getIcecek().getId());
            ps.setInt(4, stok.getTatli().getId());
            ps.setInt(5, stok.getTedarikci().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Stok> read() {
        List<Stok> stokList = new ArrayList<>();
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Stok"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // Yemek ilişkisini kontrol et
                int yemekId = rs.getInt("yemek_id");
                Yemek yemek = null;
                if (!rs.wasNull()) {
                    yemek = new YemekDAO().findById(yemekId);
                }

                // Diğer ilişkiler
                Icecek icecek = new IcecekDAO().findById(rs.getInt("icecek_id"));
                Tatli tatli = new TatliDAO().findById(rs.getInt("tatli_id"));
                Tedarikci tedarikci = new TedarikciDAO().findById(rs.getInt("tedarikci_id"));

                // Stok nesnesi oluştur ve listeye ekle
                Stok stok = new Stok(
                        rs.getInt("id"),
                        rs.getInt("miktar"),
                        yemek,
                        icecek,
                        tatli,
                        tedarikci
                );
                stokList.add(stok);
            }

        } catch (SQLException e) {
            System.out.println("StokDAO.read hatası: " + e.getMessage());
        }
        return stokList;
    }

    public void update(Stok stok) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("UPDATE Stok SET miktar = ?, yemek_id = ?, icecek_id = ?, tatli_id = ?, tedarikci_id = ? WHERE id = ?")) {

            ps.setInt(1, stok.getMiktar());
            ps.setInt(2, stok.getYemek().getId());
            ps.setInt(3, stok.getIcecek().getId());
            ps.setInt(4, stok.getTatli().getId());
            ps.setInt(5, stok.getTedarikci().getId());
            ps.setInt(6, stok.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("DELETE FROM Stok WHERE id = ?")) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Yemek findById(int id) {
        Yemek yemek = null;
        try (Connection conn = this.getConnect(); PreparedStatement ps = conn.prepareStatement("SELECT * FROM Yemek WHERE id = ?")) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                yemek = new Yemek(
                        rs.getInt("id"),
                        rs.getString("name"),
                        new MenuDAO().findById(rs.getInt("menu_id"))
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return yemek;
    }
}
