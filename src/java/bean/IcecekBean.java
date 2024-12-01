package bean;

import dao.IcecekDAO;
import entity.Icecek;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = " icecekBean")
@SessionScoped
public class IcecekBean implements Serializable {

    private IcecekDAO icecekDAO;
    private List<Icecek> icecekList;
    private Icecek selectedIcecek;

    @PostConstruct
    public void init() {
        this.icecekDAO = new IcecekDAO();
        this.icecekList = icecekDAO.read(); // DAO'dan içecek verilerini getir
        this.selectedIcecek = new Icecek();
    }

    public void create() {
        if (this.selectedIcecek != null) {
            icecekDAO.create(this.selectedIcecek); // Yeni içecek oluştur
            this.icecekList = icecekDAO.read();    // Listeyi güncelle
            this.selectedIcecek = new Icecek();   // Formu sıfırla
        }
    }

    public void update() {
        if (this.selectedIcecek != null) {
            icecekDAO.update(this.selectedIcecek); // İçeceği güncelle
            this.icecekList = icecekDAO.read();    // Listeyi güncelle
            this.selectedIcecek = new Icecek();   // Formu sıfırla
        }
    }

    public void delete(Icecek icecek) {
        if (icecek != null) {
            icecekDAO.delete(icecek.getId());  // İçeceği sil
            this.icecekList = icecekDAO.read(); // Listeyi güncelle
        }
    }

    // Getter ve Setter metotları
    public List<Icecek> getIcecekList() {
        return icecekList;
    }

    public void setIcecekList(List<Icecek> icecekList) {
        this.icecekList = icecekList;
    }

    public Icecek getSelectedIcecek() {
        return selectedIcecek;
    }

    public void setSelectedIcecek(Icecek selectedIcecek) {
        this.selectedIcecek = selectedIcecek;
    }
}
