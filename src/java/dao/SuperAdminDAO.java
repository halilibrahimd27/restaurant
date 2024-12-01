/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Admin;
import entity.SuperAdmin;
import jakarta.resource.cci.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import util.DBConnection;

public class SuperAdminDAO extends DBConnection {

    private Connection connection;

    public SuperAdmin getSuperAdmin(String username, String password) {
        SuperAdmin a = new SuperAdmin();

        a.setUsername(username);
        a.setPassword(password);
        try {

            Statement st = (Statement) this.getConnect().createStatement();
            st.executeUpdate("INSERT INTO admin (username,password) VALUES ('"
                    + a.getUsername() + "','"
                    + a.getPassword() + ")");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return a;

    }

    public List<SuperAdmin> getAdminList() {

        List<SuperAdmin> adminList = new ArrayList<>();

        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query = "SELECT * FROM admin ";
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {

                adminList.add(new SuperAdmin(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return adminList;
    }

    public void create(SuperAdmin sa) {

        try {

            Statement st = (Statement) this.getConnect().createStatement();
            st.executeUpdate("INSERT INTO admin (username, password) VALUES ('"
                    + sa.getUsername() + "','"
                    + sa.getPassword() + "')");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(SuperAdmin sa) {
        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query0 = "UPDATE admin SET id = id - 1 WHERE id > " + sa.getId();
            String query1 = "DELETE from admin where id=" + sa.getId();
            st.executeUpdate(query1);
            st.executeUpdate(query0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void update(SuperAdmin superAdmin) {
        try {
            Statement st = this.getConnect().createStatement();
            String query = "UPDATE superadmin SET "
                    + "username = '" + superAdmin.getUsername() + "', "
                    + "password = '" + superAdmin.getPassword() + "' "
                    + "WHERE id = " + superAdmin.getId();
            st.executeUpdate(query);
            System.out.println("SuperAdmin başarıyla güncellendi.");
        } catch (SQLException e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public int count() {

        int count = 0;

        List<SuperAdmin> adminList = new ArrayList<>();

        try {

            Statement st = (Statement) this.getConnect().createStatement();

            String query = "select count(id) as film_count from admin";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            count = rs.getInt("film_count");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
