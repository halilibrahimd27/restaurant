/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */
import dao.VardiyaDAO;
import entity.Vardiya;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " vardiyaBean")
@SessionScoped
public class VardiyaBean implements Serializable {

    private Vardiya entity = new Vardiya();
    private List<Vardiya> list;
    private VardiyaDAO dao = new VardiyaDAO();

    public Vardiya getEntity() {
        return entity;
    }

    public void setEntity(Vardiya entity) {
        this.entity = entity;
    }

    public List<Vardiya> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read();
        entity = new Vardiya();
    }

    public void update() {
        dao.update(entity);
        list = dao.read();
        entity = new Vardiya();
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read();
    }
}

