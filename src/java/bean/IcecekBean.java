package bean;

import dao.IcecekDAO;
import entity.Icecek;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("icecekBean")
@SessionScoped
public class IcecekBean implements Serializable {

    private IcecekDAO dao = new IcecekDAO();
    private List<Icecek> list;
    private Icecek entity = new Icecek();


    public Icecek getEntity() {
        return entity;
    }

    public void setEntity(Icecek entity) {
        this.entity = entity;
    }
    public IcecekDAO getDao() {
        if (this.dao == null) {
            this.dao = new IcecekDAO();
        }
        return dao;
    }

    public void setDao(IcecekDAO dao) {
        this.dao = dao;
    }

    public List<Icecek> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Icecek> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Icecek();
    }

    public void update(Icecek Icecek) {
        try {
            this.getDao().update(Icecek);
            System.out.println("İcecek başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Icecek sa) {
        this.getDao().delete(sa);
    }

    public Icecek getSelectedIcecek() {
        return entity;
    }

    public void setSelectedIcecek(Icecek selectedIcecek) {
        this.entity = selectedIcecek;
    }
}
