package bean;

import dao.RestaurantDAO;
import entity.Restaurant;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("restaurantBean")
@SessionScoped
public class RestaurantBean implements Serializable {

    private RestaurantDAO dao = new RestaurantDAO();
    private List<Restaurant> list;
    private Restaurant entity = new Restaurant();


    public Restaurant getEntity() {
        return entity;
    }

    public void setEntity(Restaurant entity) {
        this.entity = entity;
    }
    public RestaurantDAO getDao() {
        if (this.dao == null) {
            this.dao = new RestaurantDAO();
        }
        return dao;
    }

    public void setDao(RestaurantDAO dao) {
        this.dao = dao;
    }

    public List<Restaurant> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Restaurant> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Restaurant();
    }

    public void update(Restaurant Restaurant) {
        try {
            this.getDao().update(Restaurant);
            System.out.println("Restaurant başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Restaurant sa) {
        this.getDao().delete(sa);
    }

    public Restaurant getSelectedRestaurant() {
        return entity;
    }

    public void setSelectedRestaurant(Restaurant selectedRestaurant) {
        this.entity = selectedRestaurant;
    }
}
