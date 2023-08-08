package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.UserRepository;


@Named
@ViewScoped
public class LoginController extends AbstractMessageController implements Serializable {

    @Inject
    private UserRepository userRepository;
    
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
    
    
    public String login() {
        try {
            User loggedInUser = userRepository.findByUsername(user);
            if (loggedInUser != null && loggedInUser.getPassword().equals(user.getPassword())) {
                return "index.xhtml?faces-redirect=true"; 
            }
        } catch (NoResultException e) {
            // User not found
        }
        super.warningMessage("Login Failed!");
        return null;
    }
    
   
}
