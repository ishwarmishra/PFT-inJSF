package personalfinancetrackerinweb.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.services.LoginService;


@Named
@ViewScoped
public class LoginController extends AbstractMessageController implements Serializable {
   
    private String username;
    private String password;
    
    @Inject
    private LoginService loginService;
    
    @Inject
    private UserBean userBean;
    
    private User user;
    

    public User getUser() {
    return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
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
    
    @PostConstruct
    public void init(){
        user = new User();
    }
    
    public void userLogin() { 
        
        User loggedInUser = loginService.userLogin(username,password);
        
        if (loggedInUser != null) { 
            
                HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                     .getExternalContext().getRequest();               
                      httpServletRequest.getSession().setAttribute("loggedInClient", loggedInUser);               
                      userBean.setUser(loggedInUser);               
            try {
                  FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } 
            catch (IOException e) {   
                    e.printStackTrace();
                }
               
        }
        else 
        {              
            errorMessage("Invalid Credentials. Try Again!");
        }
    }
   
}
    
