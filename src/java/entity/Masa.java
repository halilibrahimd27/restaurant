package entity;

public class Masa {
    private int id;
    private int numara;
    private String durum;
    private int kapasite;

    public Masa() {
    }

    public Masa(int id, int numara, String durum,int kapasite) {
        this.id = id;
        this.numara = numara;
        this.durum = durum;
        this.kapasite= kapasite;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumara() {
        return numara;
    }

    public void setNumara(int numara) {
        this.numara = numara;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }
}
