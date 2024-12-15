package bean;

import dao.TatliDAO;
import entity.Tatli;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("tatliBean")
@SessionScoped
public class TatliBean implements Serializable {

    private TatliDAO dao = new TatliDAO();
    private List<Tatli> list;
    private Tatli entity = new Tatli();


    public Tatli getEntity() {
        return entity;
    }

    public void setEntity(Tatli entity) {
        this.entity = entity;
    }
    public TatliDAO getDao() {
        if (this.dao == null) {
            this.dao = new TatliDAO();
        }
        return dao;
    }

    public void setDao(TatliDAO dao) {
        this.dao = dao;
    }

    public List<Tatli> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Tatli> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Tatli();
    }

    public void update(Tatli Tatli) {
        try {
            this.getDao().update(Tatli);
            System.out.println("Tatli başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Tatli sa) {
        this.getDao().delete(sa);
    }

    public Tatli getSelectedTatli() {
        return entity;
    }

    public void setSelectedTatli(Tatli selectedTatli) {
        this.entity = selectedTatli;
    }
}
