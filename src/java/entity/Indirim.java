package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Indirim {

    private int id;
    private BigDecimal amount;
    private Date gecerlilikTarihi;

    public Indirim(int id, BigDecimal amount, Date gecerlilikTarihi, Users user) {
        this.id = id;
        this.amount = amount;
        this.gecerlilikTarihi = gecerlilikTarihi;
        this.user = user;
    }

    public Date getGecerlilikTarihi() {
        return gecerlilikTarihi;
    }

    public void setGecerlilikTarihi(Date gecerlilikTarihi) {
        this.gecerlilikTarihi = gecerlilikTarihi;
    }
    private Users user;

    public Indirim(int id, BigDecimal amount, Users user) {
        this.id = id;
        this.amount = amount;
        this.user = user;
    }

    public Indirim() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
