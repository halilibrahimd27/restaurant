package bean;

import dao.RezervasyonDAO;
import entity.Rezervasyon;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " rezervasyonBean")
@SessionScoped
public class RezervasyonBean implements Serializable {

    private Rezervasyon entity = new Rezervasyon();
    private List<Rezervasyon> list;
    private RezervasyonDAO dao = new RezervasyonDAO();

    public Rezervasyon getEntity() {
        return entity;
    }

    public void setEntity(Rezervasyon entity) {
        this.entity = entity;
    }

    public List<Rezervasyon> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Rezervasyon(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Rezervasyon(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
