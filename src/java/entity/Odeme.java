package entity;

import java.math.BigDecimal;
import java.util.Date;

public class Odeme {

    private int id;             // Ödemenin benzersiz kimliği
    private Date tarih;         // Ödeme tarihi
    private BigDecimal amount;  // Ödeme miktarı
    private Siparis siparis;    // Ödeme ile ilişkili sipariş

    // Parametreli Constructor
    public Odeme(int id, Date tarih, BigDecimal amount, Siparis siparis) {
        this.id = id;
        this.tarih = tarih;
        this.amount = amount;
        this.siparis = siparis;
    }

    // Parametresiz Constructor
    public Odeme() {
    }

    // Getter ve Setter metodları
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

    public Siparis getSiparis() {
        return siparis;
    }

    public void setSiparis(Siparis siparis) {
        this.siparis = siparis;
    }
}
