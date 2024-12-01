package bean;

import dao.TedarikciDAO;
import entity.Tedarikci;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " tedarikciBean")
@SessionScoped
public class TedarikciBean implements Serializable {

    private Tedarikci entity = new Tedarikci();
    private List<Tedarikci> list;
    private TedarikciDAO dao = new TedarikciDAO();

    public Tedarikci getEntity() {
        return entity;
    }

    public void setEntity(Tedarikci entity) {
        this.entity = entity;
    }

    public List<Tedarikci> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Tedarikci(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Tedarikci(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
