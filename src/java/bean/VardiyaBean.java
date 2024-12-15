package bean;

import dao.VardiyaDAO;
import entity.Vardiya;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("vardiyaBean")
@SessionScoped
public class VardiyaBean implements Serializable {

    private VardiyaDAO dao = new VardiyaDAO();
    private List<Vardiya> list;
    private Vardiya entity = new Vardiya();


    public Vardiya getEntity() {
        return entity;
    }

    public void setEntity(Vardiya entity) {
        this.entity = entity;
    }
    public VardiyaDAO getDao() {
        if (this.dao == null) {
            this.dao = new VardiyaDAO();
        }
        return dao;
    }

    public void setDao(VardiyaDAO dao) {
        this.dao = dao;
    }

    public List<Vardiya> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Vardiya> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Vardiya();
    }

    public void update(Vardiya Vardiya) {
        try {
            this.getDao().update(Vardiya);
            System.out.println("Vardiya başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Vardiya sa) {
        this.getDao().delete(sa);
    }

    public Vardiya getSelectedVardiya() {
        return entity;
    }

    public void setSelectedVardiya(Vardiya selectedVardiya) {
        this.entity = selectedVardiya;
    }
}
