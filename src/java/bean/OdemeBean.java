package bean;

import dao.OdemeDAO;
import entity.Odeme;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("odemeBean")
@SessionScoped
public class OdemeBean implements Serializable {

    private OdemeDAO dao = new OdemeDAO();
    private List<Odeme> list;
    private Odeme entity = new Odeme();


    public Odeme getEntity() {
        return entity;
    }

    public void setEntity(Odeme entity) {
        this.entity = entity;
    }
    public OdemeDAO getDao() {
        if (this.dao == null) {
            this.dao = new OdemeDAO();
        }
        return dao;
    }

    public void setDao(OdemeDAO dao) {
        this.dao = dao;
    }

    public List<Odeme> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Odeme> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Odeme();
    }

    public void update(Odeme Odeme) {
        try {
            this.getDao().update(Odeme);
            System.out.println("Odeme başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Odeme sa) {
        this.getDao().delete(sa);
    }

    public Odeme getSelectedOdeme() {
        return entity;
    }

    public void setSelectedOdeme(Odeme selectedOdeme) {
        this.entity = selectedOdeme;
    }
}
