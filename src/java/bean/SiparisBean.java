package bean;

import dao.SiparisDAO;
import entity.Siparis;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("siparisBean")
@SessionScoped
public class SiparisBean implements Serializable {

    private SiparisDAO dao = new SiparisDAO();
    private List<Siparis> list;
    private Siparis entity = new Siparis();


    public Siparis getEntity() {
        return entity;
    }

    public void setEntity(Siparis entity) {
        this.entity = entity;
    }
    public SiparisDAO getDao() {
        if (this.dao == null) {
            this.dao = new SiparisDAO();
        }
        return dao;
    }

    public void setDao(SiparisDAO dao) {
        this.dao = dao;
    }

    public List<Siparis> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Siparis> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Siparis();
    }

    public void update(Siparis Siparis) {
        try {
            this.getDao().update(Siparis);
            System.out.println("Siparis başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Siparis sa) {
        this.getDao().delete(sa);
    }

    public Siparis getSelectedSiparis() {
        return entity;
    }

    public void setSelectedSiparis(Siparis selectedSiparis) {
        this.entity = selectedSiparis;
    }
}
