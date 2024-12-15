package bean;

import dao.TeslimatDAO;
import entity.Teslimat;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("teslimatBean")
@SessionScoped
public class TeslimatBean implements Serializable {

    private TeslimatDAO dao = new TeslimatDAO();
    private List<Teslimat> list;
    private Teslimat entity = new Teslimat();


    public Teslimat getEntity() {
        return entity;
    }

    public void setEntity(Teslimat entity) {
        this.entity = entity;
    }
    public TeslimatDAO getDao() {
        if (this.dao == null) {
            this.dao = new TeslimatDAO();
        }
        return dao;
    }

    public void setDao(TeslimatDAO dao) {
        this.dao = dao;
    }

    public List<Teslimat> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Teslimat> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Teslimat();
    }

    public void update(Teslimat Teslimat) {
        try {
            this.getDao().update(Teslimat);
            System.out.println("Teslimat başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Teslimat sa) {
        this.getDao().delete(sa);
    }

    public Teslimat getSelectedTeslimat() {
        return entity;
    }

    public void setSelectedTeslimat(Teslimat selectedTeslimat) {
        this.entity = selectedTeslimat;
    }
}
