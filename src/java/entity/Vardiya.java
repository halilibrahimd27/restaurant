package entity;

public class Vardiya {

    private int id;              // Vardiya benzersiz kimliği
    private String saatler;      // Vardiya saatleri
    private Calisan calisan;     // Vardiya ile ilişkilendirilmiş çalışan

    public Vardiya(int id, String saatler, Calisan calisan) {
        this.id = id;
        this.saatler = saatler;
        this.calisan = calisan;
    }

    public Vardiya() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaatler() {
        return saatler;
    }

    public void setSaatler(String saatler) {
        this.saatler = saatler;
    }

    public Calisan getCalisan() {
        return calisan;
    }

    public void setCalisan(Calisan calisan) {
        this.calisan = calisan;
    }
}
