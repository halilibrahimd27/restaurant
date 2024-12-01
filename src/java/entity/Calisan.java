package entity;

public class Calisan {

    private int id;          // Çalışanın benzersiz kimliği
    private String name;     // Çalışanın adı

    public Calisan(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Calisan() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
