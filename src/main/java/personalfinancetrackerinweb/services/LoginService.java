package personalfinancetrackerinweb.services;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import org.mindrot.jbcrypt.BCrypt;
import personalfinancetrackerinweb.controller.AbstractMessageController;
import personalfinancetrackerinweb.controller.UserBean;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.UserRepository;

@Stateless
public class LoginService extends AbstractMessageController implements Serializable {
   
    
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
    
    public User userLogin(String username,String password) {       
        User loggedInUser = userRepository.findByUsername(username);

        User loginUser=null;
        if (loggedInUser != null) { 
        if (BCrypt.checkpw(password, loggedInUser.getPassword()))  {               
        userBean.setUser(loggedInUser);               
         loginUser=loggedInUser;
           }
          }
     return loginUser;
    }
}