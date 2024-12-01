package entity;

public class Tedarikci {
    private int id;
    private String name;
    private String phone;
    private Restaurant restaurant;

    public Tedarikci() {
    }

    public Tedarikci(int id, String name, String phone, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.restaurant = restaurant;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
