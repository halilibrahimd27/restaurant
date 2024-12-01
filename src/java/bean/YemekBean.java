package bean;

import dao.YemekDAO;
import entity.Yemek;
import entity.Menu;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named(value = "yemekBean")
@SessionScoped
public class YemekBean implements Serializable {

    private YemekDAO yemekDAO;      // Yemek DAO sınıfı
    private List<Yemek> yemekList; // Yemek kayıtları
    private Yemek selectedYemek;   // Yemek oluşturma/güncelleme işlemleri için seçilen nesne

    private List<Menu> menuList;   // Menü listesi
    private Menu selectedMenu;     // Seçilen Menü

    @PostConstruct
    public void init() {
        this.yemekDAO = new YemekDAO();         // DAO örneği oluştur
        this.yemekList = yemekDAO.read();       // Yemek listesini getir
        this.selectedYemek = new Yemek();      // Boş bir Yemek nesnesi oluştur
    }

    public void create() {
        if (this.selectedYemek != null) {
            this.selectedYemek.setMenu(this.selectedMenu);  // Menü ilişkisini ayarla
            yemekDAO.create(this.selectedYemek);           // DAO'da oluştur
            this.yemekList = yemekDAO.read();              // Listeyi güncelle
            this.selectedYemek = new Yemek();              // Formu sıfırla
        }
    }

    public void update() {
        if (this.selectedYemek != null) {
            this.selectedYemek.setMenu(this.selectedMenu);
            yemekDAO.update(this.selectedYemek);
            this.yemekList = yemekDAO.read();
            this.selectedYemek = new Yemek();
        }
    }

    public void delete(Yemek yemek) {
        if (yemek != null) {
            yemekDAO.delete(yemek.getId());
            this.yemekList = yemekDAO.read();
        }
    }

    // Getter ve Setter
    public List<Yemek> getYemekList() {
        return yemekList;
    }

    public void setYemekList(List<Yemek> yemekList) {
        this.yemekList = yemekList;
    }

    public Yemek getSelectedYemek() {
        return selectedYemek;
    }

    public void setSelectedYemek(Yemek selectedYemek) {
        this.selectedYemek = selectedYemek;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getSelectedMenu() {
        return selectedMenu;
    }

    public void setSelectedMenu(Menu selectedMenu) {
        this.selectedMenu = selectedMenu;
    }
}
