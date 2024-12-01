package entity;

public class Users {

    private int id;              // Kullanıcının benzersiz kimliği
    private String userName;     // Kullanıcının adı
    private String adress;       // Kullanıcının adresi
    private String password;     // Kullanıcının şifresi

    public Users(int id, String userName, String adress, String password) {
        this.id = id;
        this.userName = userName;
        this.adress = adress;
        this.password = password;
    }

    public Users() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
