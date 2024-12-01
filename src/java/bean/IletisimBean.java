/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */
import dao.IletisimDAO;
import entity.Iletisim;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " iletisimBean")
@SessionScoped
public class IletisimBean implements Serializable {

    private Iletisim entity = new Iletisim();
    private List<Iletisim> list;
    private IletisimDAO dao = new IletisimDAO();

    public Iletisim getEntity() {
        return entity;
    }

    public void setEntity(Iletisim entity) {
        this.entity = entity;
    }

    public List<Iletisim> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read();
        entity = new Iletisim();
    }

    public void update() {
        dao.update(entity);
        list = dao.read();
        entity = new Iletisim();
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read();
    }
}

