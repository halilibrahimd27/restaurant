package entity;

public class Teslimat {
    private int id;
    private String address;
    private String durum;
    private Restaurant restaurant; // Restaurant referansı
    private Siparis siparis;       // Sipariş referansı

    public Teslimat() {
    }

    public Teslimat(int id, String address, String durum, Restaurant restaurant, Siparis siparis) {
        this.id = id;
        this.address = address;
        this.durum = durum;
        this.restaurant = restaurant;
        this.siparis = siparis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Siparis getSiparis() {
        return siparis;
    }

    public void setSiparis(Siparis siparis) {
        this.siparis = siparis;
    }
}
