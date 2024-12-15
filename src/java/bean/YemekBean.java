package bean;

import dao.YemekDAO;
import entity.Yemek;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("yemekBean")
@SessionScoped
public class YemekBean implements Serializable {

    private YemekDAO dao = new YemekDAO();
    private List<Yemek> list;
    private Yemek entity = new Yemek();


    public Yemek getEntity() {
        return entity;
    }

    public void setEntity(Yemek entity) {
        this.entity = entity;
    }
    public YemekDAO getDao() {
        if (this.dao == null) {
            this.dao = new YemekDAO();
        }
        return dao;
    }

    public void setDao(YemekDAO dao) {
        this.dao = dao;
    }

    public List<Yemek> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Yemek> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Yemek();
    }

    public void update(Yemek Yemek) {
        try {
            this.getDao().update(Yemek);
            System.out.println("Yemek başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Yemek sa) {
        this.getDao().delete(sa);
    }

    public Yemek getSelectedYemek() {
        return entity;
    }

    public void setSelectedYemek(Yemek selectedYemek) {
        this.entity = selectedYemek;
    }
}
