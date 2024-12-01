package bean;

import dao.TatliDAO;
import entity.Tatli;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " tatliBean")
@SessionScoped
public class TatliBean implements Serializable {

    private Tatli entity = new Tatli();
    private List<Tatli> list;
    private TatliDAO dao = new TatliDAO();

    public Tatli getEntity() {
        return entity;
    }

    public void setEntity(Tatli entity) {
        this.entity = entity;
    }

    public List<Tatli> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Tatli(); // Formu sıfırla
    }

    public void update() {
        dao.update(entity);
        list = dao.read(); // Listeyi güncelle
        entity = new Tatli(); // Formu sıfırla
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read(); // Listeyi güncelle
    }
}
