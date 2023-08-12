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

    @PostConstruct
    public void init(){
        user = new User();
    }
    
    public String userLogin() {       
        User loggedInUser = userRepository.findByUsernameAndPassword(user);
        if (loggedInUser != null) { 
            
        if (loggedInUser.getPassword().equals(user.getPassword())) {               
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