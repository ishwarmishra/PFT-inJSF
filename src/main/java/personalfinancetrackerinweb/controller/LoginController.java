package personalfinancetrackerinweb.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class LoginController extends  AbstractMessageController {
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
}  
    
    
    
            
    
    
