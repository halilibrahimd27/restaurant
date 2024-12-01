package entity;

public class Yemek {

    private int id;          // Benzersiz ID
    private String name;     // Yemek adı
    private Menu menu;       // Yemek ile ilişkilendirilmiş Menu

    public Yemek(int id, String name, Menu menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
    }

    public Yemek() {
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
