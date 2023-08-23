package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.acl.UserRole;
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
    
     public UserRole[] getUserRoles() {
        return UserRole.values();
    }

    @PostConstruct
    public void init() {
        user = new User();
        List<String> usernameList = userRepository.getUserList();
    }

    public void beforeCreate() {
        user = new User();
    }

    public void saveData() {
        if (user.getId() == 0) {
            List<String> usernameList = userRepository.getUserList();

            for (String username : usernameList) {
                if (username != null) {
                    if (username.equals(user.getUsername())) {
                        super.warningMessage("Pleae Give the Unique username!");
                        return;
                    }
                }
            }
            userRepository.create(user);
            super.infoMessage("Sign Up Successfully!");
        }
    }
    public void deleteData(User user) {
        userRepository.delete(user.getId());
        user = new User();
        findAll();  
        super.warningMessage("Data deleted successfully!");
        return;
    }

    public void findAll() {
        userList = userRepository.findByUser(user.getId());
    }
}
