package entity;

public class Iletisim {
    private int id;
    private Users user;
    private Restaurant restaurant;
    private String mesaj;

    public Iletisim() {
    }

    public Iletisim(int id, Users user, Restaurant restaurant, String mesaj) {
        this.id = id;
        this.user = user;
        this.restaurant = restaurant;
        this.mesaj = mesaj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getMesaj() {
        return mesaj;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }
}
