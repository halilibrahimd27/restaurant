package entity;

import java.util.Date;

public class Rezervasyon {

    private int id;
    private Date tarih;
    private String saat;
    private Users user;
    private Masa masa;

    public Rezervasyon(int id, Date tarih, String saat, Users user, Masa masa) {
        this.id = id;
        this.tarih = tarih;
        this.saat = saat;
        this.user = user;
        this.masa = masa;
    }

    public Rezervasyon() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTarih() {
        return tarih;
    }

    public void setTarih(Date tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Masa getMasa() {
        return masa;
    }

    public void setMasa(Masa masa) {
        this.masa = masa;
    }
}
