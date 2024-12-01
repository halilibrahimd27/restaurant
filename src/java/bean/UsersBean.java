package bean;

import dao.UsersDAO;
import entity.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " usersBean")
@SessionScoped
public class UsersBean implements Serializable {

    private Users entity = new Users();
    private List<Users> list;
    private UsersDAO dao = new UsersDAO();

    public Users getEntity() {
        return entity;
    }

    public void setEntity(Users entity) {
        this.entity = entity;
    }

    public List<Users> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Users(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Users(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
