package bean;

import dao.GeriBildirimDAO;
import entity.GeriBildirim;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("geriBildirimBean")
@SessionScoped
public class GeriBildirimBean implements Serializable {

    private GeriBildirim entity = new GeriBildirim();
    private List<GeriBildirim> list;
    private GeriBildirimDAO dao = new GeriBildirimDAO();

    public GeriBildirim getEntity() {
        return entity;
    }

    public void setEntity(GeriBildirim entity) {
        this.entity = entity;
    }

    public GeriBildirimDAO getDao() {
        if (this.dao == null) {
            this.dao = new GeriBildirimDAO();
        }
        return dao;
    }

    public void setDao(GeriBildirimDAO dao) {
        this.dao = dao;
    }

    public List<GeriBildirim> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<GeriBildirim> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new GeriBildirim();
    }

    public void update(GeriBildirim GeriBildirim) {
        try {
            this.getDao().update(GeriBildirim);
            System.out.println("Geri Bildirim başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(GeriBildirim sa) {
        this.getDao().delete(sa);
    }
}
