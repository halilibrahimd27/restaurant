package bean;

import dao.IndirimDAO;
import entity.Indirim;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("indirimBean")
@SessionScoped
public class IndirimBean implements Serializable {

    private IndirimDAO dao = new IndirimDAO();
    private List<Indirim> list;
    private Indirim entity = new Indirim();


    public Indirim getEntity() {
        return entity;
    }

    public void setEntity(Indirim entity) {
        this.entity = entity;
    }
    public IndirimDAO getDao() {
        if (this.dao == null) {
            this.dao = new IndirimDAO();
        }
        return dao;
    }

    public void setDao(IndirimDAO dao) {
        this.dao = dao;
    }

    public List<Indirim> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Indirim> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Indirim();
    }

    public void update(Indirim Indirim) {
        try {
            this.getDao().update(Indirim);
            System.out.println("Indirim başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Indirim sa) {
        this.getDao().delete(sa);
    }

    public Indirim getSelectedIndirim() {
        return entity;
    }

    public void setSelectedIndirim(Indirim selectedIndirim) {
        this.entity = selectedIndirim;
    }
}
