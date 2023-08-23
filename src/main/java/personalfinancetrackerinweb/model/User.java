package personalfinancetrackerinweb.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.mindrot.jbcrypt.BCrypt;
import personalfinancetrackerinweb.acl.UserRole;

@Entity
@Table(name = "user_entity")
public class User extends AbstractEntity implements Serializable {

    private String username;
    private String password;
    
         
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User() {
        
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
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    
}
