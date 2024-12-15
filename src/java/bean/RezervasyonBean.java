package bean;

import dao.RezervasyonDAO;
import entity.Rezervasyon;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("rezervasyonBean")
@SessionScoped
public class RezervasyonBean implements Serializable {

    private RezervasyonDAO dao = new RezervasyonDAO();
    private List<Rezervasyon> list;
    private Rezervasyon entity = new Rezervasyon();


    public Rezervasyon getEntity() {
        return entity;
    }

    public void setEntity(Rezervasyon entity) {
        this.entity = entity;
    }
    public RezervasyonDAO getDao() {
        if (this.dao == null) {
            this.dao = new RezervasyonDAO();
        }
        return dao;
    }

    public void setDao(RezervasyonDAO dao) {
        this.dao = dao;
    }

    public List<Rezervasyon> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Rezervasyon> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Rezervasyon();
    }

    public void update(Rezervasyon Rezervasyon) {
        try {
            this.getDao().update(Rezervasyon);
            System.out.println("Rezervasyon başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Rezervasyon sa) {
        this.getDao().delete(sa);
    }

    public Rezervasyon getSelectedRezervasyon() {
        return entity;
    }

    public void setSelectedRezervasyon(Rezervasyon selectedRezervasyon) {
        this.entity = selectedRezervasyon;
    }
}
