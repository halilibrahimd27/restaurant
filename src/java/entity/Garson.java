package entity;

public class Garson {
    private int id;
    private String name;
    private Calisan calisan; // Çalışan referansı

    public Garson() {
    }

    public Garson(int id, String name, Calisan calisan) {
        this.id = id;
        this.name = name;
        this.calisan = calisan;
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

    public Calisan getCalisan() {
        return calisan;
    }

    public void setCalisan(Calisan calisan) {
        this.calisan = calisan;
    }
}
