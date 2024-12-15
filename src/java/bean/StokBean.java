package bean;

import dao.StokDAO;
import entity.Stok;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("stokBean")
@SessionScoped
public class StokBean implements Serializable {

    private StokDAO dao = new StokDAO();
    private List<Stok> list;
    private Stok entity = new Stok();


    public Stok getEntity() {
        return entity;
    }

    public void setEntity(Stok entity) {
        this.entity = entity;
    }
    public StokDAO getDao() {
        if (this.dao == null) {
            this.dao = new StokDAO();
        }
        return dao;
    }

    public void setDao(StokDAO dao) {
        this.dao = dao;
    }

    public List<Stok> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Stok> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Stok();
    }

    public void update(Stok Stok) {
        try {
            this.getDao().update(Stok);
            System.out.println("Stok başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Stok sa) {
        this.getDao().delete(sa);
    }

    public Stok getSelectedStok() {
        return entity;
    }

    public void setSelectedStok(Stok selectedStok) {
        this.entity = selectedStok;
    }
}
