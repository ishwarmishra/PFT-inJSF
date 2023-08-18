package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.UserRepository;


@Named
@ViewScoped
public class SignupController extends AbstractMessageController implements Serializable {

    @Inject
    private UserRepository userRepository;
    
    private User user;
    
    private List<User> userList;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    
    @PostConstruct
    public void init(){
        user = new User();
    }
    public void beforeCreate() {
        user = new User();
    }

    
     public void saveData() {
        if (user.getId() == 0) {
            userRepository.create(user);
            user=new User();
            super.infoMessage( "Sign Up successfully!");

        } else {
            userRepository.update(user);
            user=new User();
            super.infoMessage( " Password changed successfully!");

        }
        user = new User();
        findAll();

    }
    public void deleteData(User user) {
        userRepository.delete(user.getId());//In AbstractGeneric Method
        user = new  User();
        findAll();//After deletion new records of the singup is populates to 'incomeList'  
        super.warningMessage("Data deleted successfully!");
        return;
        
    } 
    
    public void findAll() {
        userList = userRepository.findByUser(user.getId());
    }

}
    
    
