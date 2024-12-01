package bean;

import dao.SiparisDAO;
import entity.Siparis;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " siparisBean")
@SessionScoped
public class SiparisBean implements Serializable {

    private Siparis entity = new Siparis();
    private List<Siparis> list;
    private SiparisDAO dao = new SiparisDAO();

    public Siparis getEntity() {
        return entity;
    }

    public void setEntity(Siparis entity) {
        this.entity = entity;
    }

    public List<Siparis> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Siparis(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Siparis(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
