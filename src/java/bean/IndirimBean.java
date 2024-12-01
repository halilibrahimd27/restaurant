/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */
import dao.IndirimDAO;
import entity.Indirim;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " indirimBean")
@SessionScoped
public class IndirimBean implements Serializable {

    private Indirim entity = new Indirim();
    private List<Indirim> list;
    private IndirimDAO dao = new IndirimDAO();

    public Indirim getEntity() {
        return entity;
    }

    public void setEntity(Indirim entity) {
        this.entity = entity;
    }

    public List<Indirim> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read();
        entity = new Indirim();
    }

    public void update() {
        dao.update(entity);
        list = dao.read();
        entity = new Indirim();
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read();
    }
}
