package bean;

import dao.StokDAO;
import entity.Stok;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " stokBean")
@SessionScoped
public class StokBean implements Serializable {

    private Stok entity = new Stok();
    private List<Stok> list;
    private StokDAO dao = new StokDAO();

    public Stok getEntity() {
        return entity;
    }

    public void setEntity(Stok entity) {
        this.entity = entity;
    }

    public List<Stok> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Stok(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Stok(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
