package entity;

public class Tatli {
    private int id;
    private String name;
    private Menu menu;

    public Tatli() {
    }

    public Tatli(int id, String name, Menu menu) {
        this.id = id;
        this.name = name;
        this.menu = menu;
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
