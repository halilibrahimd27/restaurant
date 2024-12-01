package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Siparis {

    private int id;
    private Date tarih;
    private BigDecimal amount;
    private Users user;
    private Masa masa;

    public Siparis(int id, Date tarih, BigDecimal amount, Users user, Masa masa) {
        this.id = id;
        this.tarih = tarih;
        this.amount = amount;
        this.user = user;
        this.masa = masa;
    }

    public Siparis() {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
