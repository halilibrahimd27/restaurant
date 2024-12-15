package bean;

import dao.MenuDAO;
import entity.Menu;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("menuBean")
@SessionScoped
public class MenuBean implements Serializable {

    private MenuDAO dao = new MenuDAO();
    private List<Menu> list;
    private Menu entity = new Menu();


    public Menu getEntity() {
        return entity;
    }

    public void setEntity(Menu entity) {
        this.entity = entity;
    }
    public MenuDAO getDao() {
        if (this.dao == null) {
            this.dao = new MenuDAO();
        }
        return dao;
    }

    public void setDao(MenuDAO dao) {
        this.dao = dao;
    }

    public List<Menu> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Menu> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Menu();
    }

    public void update(Menu Menu) {
        try {
            this.getDao().update(Menu);
            System.out.println("Menu başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Menu sa) {
        this.getDao().delete(sa);
    }

    public Menu getSelectedMenu() {
        return entity;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.entity = selectedMenu;
    }
}
