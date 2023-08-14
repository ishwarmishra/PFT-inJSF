package personalfinancetrackerinweb.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.mindrot.jbcrypt.BCrypt;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.UserRepository;


@Named
@ViewScoped
public class LoginController extends AbstractMessageController implements Serializable {
   
    private String username;
    private String password;
    
    @Inject
    private UserRepository userRepository;
    
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
    
    public String userLogin() {       
        User loggedInUser = userRepository.findByUsername(username);
        if (loggedInUser != null) { 
            
       if (BCrypt.checkpw(password, loggedInUser.getPassword()))  {               
                HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                        .getExternalContext().getRequest();               
                       httpServletRequest.getSession().setAttribute("loggedInClient", user);               
                       userBean.setUser(user);               
            try {
                  FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                 } catch (IOException e) {   
                    e.printStackTrace();
                }
               }

        else 
        {              
            errorMessage("Invalid Credentials. Try Again!");
            }
        } else 
        {
            errorMessage("Invalid Credentials. Try Again!");
        }
        return null;
    }
   
}
    
// if (loggedInUser.getPassword().equals(user.getPassword()))   
  
//if (BCrypt.checkpw(password, user.getPassword())) 