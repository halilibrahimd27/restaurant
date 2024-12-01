/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */
import dao.TeslimatDAO;
import entity.Teslimat;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " teslimatBean")
@SessionScoped
public class TeslimatBean implements Serializable {

    private Teslimat entity = new Teslimat();
    private List<Teslimat> list;
    private TeslimatDAO dao = new TeslimatDAO();

    public Teslimat getEntity() {
        return entity;
    }

    public void setEntity(Teslimat entity) {
        this.entity = entity;
    }

    public List<Teslimat> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read();
        entity = new Teslimat();
    }

    public void update() {
        dao.update(entity);
        list = dao.read();
        entity = new Teslimat();
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read();
    }
}
