package bean;

import dao.IletisimDAO;
import entity.Iletisim;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named("iletisimBean")
@SessionScoped
public class IletisimBean implements Serializable {

    private IletisimDAO dao = new IletisimDAO();
    private List<Iletisim> list;
    private Iletisim entity = new Iletisim();


    public Iletisim getEntity() {
        return entity;
    }

    public void setEntity(Iletisim entity) {
        this.entity = entity;
    }
    public IletisimDAO getDao() {
        if (this.dao == null) {
            this.dao = new IletisimDAO();
        }
        return dao;
    }

    public void setDao(IletisimDAO dao) {
        this.dao = dao;
    }

    public List<Iletisim> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Iletisim> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Iletisim();
    }

    public void update(Iletisim Iletisim) {
        try {
            this.getDao().update(Iletisim);
            System.out.println("İletişim başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Iletisim sa) {
        this.getDao().delete(sa);
    }

    public Iletisim getSelectedIletisim() {
        return entity;
    }

    public void setSelectedIletisim(Iletisim selectedIletisim) {
        this.entity = selectedIletisim;
    }
}
