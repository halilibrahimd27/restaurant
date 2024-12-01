package bean;

import dao.GeriBildirimDAO;
import entity.GeriBildirim;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " geriBildirimBean")
@SessionScoped
public class GeriBildirimBean implements Serializable {

    private GeriBildirim entity = new GeriBildirim();
    private List<GeriBildirim> list;
    private GeriBildirimDAO dao = new GeriBildirimDAO();

    public GeriBildirim getEntity() {
        return entity;
    }

    public void setEntity(GeriBildirim entity) {
        this.entity = entity;
    }

    public List<GeriBildirim> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new GeriBildirim(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new GeriBildirim(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
