package bean;

import dao.RestaurantDAO;
import entity.Restaurant;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " restoranBean")
@SessionScoped
public class RestaurantBean implements Serializable {

    private Restaurant entity = new Restaurant();
    private List<Restaurant> list;
    private RestaurantDAO dao = new RestaurantDAO();

    public Restaurant getEntity() {
        return entity;
    }

    public void setEntity(Restaurant entity) {
        this.entity = entity;
    }

    public List<Restaurant> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Restaurant(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Restaurant(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
