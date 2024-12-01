/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

/**
 *
 * @author Halil
 */
import dao.GarsonDAO;
import entity.Garson;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = " garsonBean")
@SessionScoped
public class GarsonBean implements Serializable {

    private Garson entity = new Garson();
    private List<Garson> list;
    private GarsonDAO dao = new GarsonDAO();

    public Garson getEntity() {
        return entity;
    }

    public void setEntity(Garson entity) {
        this.entity = entity;
    }

    public List<Garson> getList() {
        if (list == null) {
            list = dao.read();
        }
        return list;
    }

    public void create() {
        dao.create(entity);
        list = dao.read();
        entity = new Garson();
    }

    public void update() {
        dao.update(entity);
        list = dao.read();
        entity = new Garson();
    }

    public void delete(int id) {
        dao.delete(id);
        list = dao.read();
    }
}
