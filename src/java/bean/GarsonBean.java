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

@Named("garsonBean")
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

    public GarsonDAO getDao() {
        if (this.dao == null) {
            this.dao = new GarsonDAO();
        }
        return dao;
    }

    public void setDao(GarsonDAO dao) {
        this.dao = dao;
    }

    public List<Garson> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Garson> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Garson();
    }

    public void update(Garson Garson) {
        try {
            this.getDao().update(Garson);
            System.out.println("Garson başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Garson sa) {
        this.getDao().delete(sa);
    }
}
