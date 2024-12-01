package bean;

import dao.MenuDAO;
import entity.Menu;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    private Menu entity = new Menu();
    private List<Menu> list;
    private MenuDAO dao = new MenuDAO();

    public Menu getEntity() {
        return entity;
    }

    public void setEntity(Menu entity) {
        this.entity = entity;
    }

    public List<Menu> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Menu(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Menu(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
