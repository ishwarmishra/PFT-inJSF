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
    
    public String clientLogin() {       
        User loggedInUser = userRepository.findByUsername(user);
        if (loggedInUser != null) {           
            if (loggedInUser.getPassword().equals(user.getPassword())) {              
            HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance() .getExternalContext().getRequest();               
            httpServletRequest.getSession().setAttribute("loggedInClient", loggedInUser); 
            
            userBean.setUser(loggedInUser);             
            try {
                    FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
                } catch (IOException e)
                {                  
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
    
    
