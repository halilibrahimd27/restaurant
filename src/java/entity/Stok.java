package entity;

public class Stok {
    private int id;
    private int miktar;
    private Yemek yemek;     // Yemek için referans ekledim.
    private Icecek icecek;   // Icecek için referans ekledim.
    private Tatli tatli;     // Tatlı için yeni referans ekledim.
    private Tedarikci tedarikci;

    public Stok() {
    }

    public Stok(int id, int miktar, Yemek yemek, Icecek icecek, Tatli tatli, Tedarikci tedarikci) {
        this.id = id;
        this.miktar = miktar;
        this.yemek = yemek;
        this.icecek = icecek;
        this.tatli = tatli;
        this.tedarikci = tedarikci;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMiktar() {
        return miktar;
    }

    public void setMiktar(int miktar) {
        this.miktar = miktar;
    }

    public Yemek getYemek() {
        return yemek;
    }

    public void setYemek(Yemek yemek) {
        this.yemek = yemek;
    }

    public Icecek getIcecek() {
        return icecek;
    }

    public void setIcecek(Icecek icecek) {
        this.icecek = icecek;
    }

    public Tatli getTatli() {
        return tatli;
    }

    public void setTatli(Tatli tatli) {
        this.tatli = tatli;
    }

    public Tedarikci getTedarikci() {
        return tedarikci;
    }

    public void setTedarikci(Tedarikci tedarikci) {
        this.tedarikci = tedarikci;
    }
}
