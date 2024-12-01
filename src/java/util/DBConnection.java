package util;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class DBConnection {

    // Singleton için tek bir instance
    private static Connection connection = null;

    public Connection getConnect() {
        if (connection == null) { // Bağlantı daha önce oluşturulmadıysa oluştur
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/Restaurant",
                        "postgres",
                        "12345"
                );
                System.out.println("Veritabanı bağlantısı başarılı!");
            } catch (Exception ex) {
                System.err.println("Veritabanı bağlantı hatası: " + ex.getMessage());
            }
        }
        return connection; // Var olan bağlantıyı döndür
    }
}
