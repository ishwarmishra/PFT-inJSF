package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import personalfinancetrackerinweb.model.User;


@Named
@SessionScoped
public class UserBean implements Serializable{
    
    private  User user;

    public UserBean() {    
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
        
}