package bean;

import entity.Admin;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named("adminBean")
@SessionScoped
public class AdminBean extends GenericBean<Admin> {

    public AdminBean() {
        super(Admin.class);
    }

}