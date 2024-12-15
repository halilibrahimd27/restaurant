package bean;

import dao.TedarikciDAO;
import entity.Tedarikci;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("tedarikciBean")
@SessionScoped
public class TedarikciBean implements Serializable {

    private TedarikciDAO dao = new TedarikciDAO();
    private List<Tedarikci> list;
    private Tedarikci entity = new Tedarikci();


    public Tedarikci getEntity() {
        return entity;
    }

    public void setEntity(Tedarikci entity) {
        this.entity = entity;
    }
    public TedarikciDAO getDao() {
        if (this.dao == null) {
            this.dao = new TedarikciDAO();
        }
        return dao;
    }

    public void setDao(TedarikciDAO dao) {
        this.dao = dao;
    }

    public List<Tedarikci> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Tedarikci> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Tedarikci();
    }

    public void update(Tedarikci Tedarikci) {
        try {
            this.getDao().update(Tedarikci);
            System.out.println("Tedarikci başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Tedarikci sa) {
        this.getDao().delete(sa);
    }

    public Tedarikci getSelectedTedarikci() {
        return entity;
    }

    public void setSelectedTedarikci(Tedarikci selectedTedarikci) {
        this.entity = selectedTedarikci;
    }
}
