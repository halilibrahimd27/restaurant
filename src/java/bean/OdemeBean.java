/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */
import dao.OdemeDAO;
import entity.Odeme;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " odemeBean")
@SessionScoped
public class OdemeBean implements Serializable {

    private Odeme entity = new Odeme();
    private List<Odeme> list;
    private OdemeDAO dao = new OdemeDAO();

    public Odeme getEntity() {
        return entity;
    }

    public void setEntity(Odeme entity) {
        this.entity = entity;
    }

    public List<Odeme> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read();
        entity = new Odeme();
    }

    public void update() {
        dao.update(entity);
        list = dao.read();
        entity = new Odeme();
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read();
    }
}

