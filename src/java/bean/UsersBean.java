package bean;

import dao.UsersDAO;
import entity.Users;
import java.sql.*;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;

@Named("usersBean")
@SessionScoped
public class UsersBean implements Serializable {

    private UsersDAO dao = new UsersDAO();
    private List<Users> list;
    private Users entity = new Users();

    public String login() {
        if (getDao().isValidUser(this.entity.getUserName(), entity.getPassword())) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.getExternalContext().getSessionMap().put("validUser", entity);
            try {
                ExternalContext externalContext = fc.getExternalContext();
                externalContext.redirect("AnaSayfa.xhtml");
                return "AnaSayfa.xhtml";
            } catch (IOException e) {
                System.out.println("Şifre: " + entity.getPassword());
                e.printStackTrace();
            }
            return null;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kullanıcı adı veya şifre hatalı"));
            System.out.println("Şifre hatalı");
            return null;
        }
    }

    public String logout() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getExternalContext().getSessionMap().put("validUser", null);
        ExternalContext externalContext = fc.getExternalContext();
        externalContext.redirect("AnaSayfa.xhtml");
        return "AnaSayfa.xhtml";
    }

    public Users getEntity() {
        return entity;
    }

    public void setEntity(Users entity) {
        this.entity = entity;
    }

    public UsersDAO getDao() {
        if (this.dao == null) {
            this.dao = new UsersDAO();
        }
        return dao;
    }

    public void setDao(UsersDAO dao) {
        this.dao = dao;
    }

    public List<Users> getList() {
        this.list = getDao().read();
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    public void create() {
        this.getDao().create(entity);
        this.entity = new Users();
    }

    public void update(Users Users) {
        try {
            this.getDao().update(Users);
            System.out.println("Users başarıyla güncellendi.");
        } catch (Exception e) {
            System.out.println("Güncelleme sırasında hata: " + e.getMessage());
        }
    }

    public void delete(Users sa) {
        this.getDao().delete(sa);
    }

    public Users getSelectedUsers() {
        return entity;
    }

    public void setSelectedUsers(Users selectedUsers) {
        this.entity = selectedUsers;
    }
}
