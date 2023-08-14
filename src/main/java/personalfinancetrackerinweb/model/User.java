package personalfinancetrackerinweb.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.mindrot.jbcrypt.BCrypt;

@Entity
@Table(name = "user_entity")
public class User extends AbstractEntity implements Serializable {

   
    private String username;
    private String password;
    private String roles; 
    
    public User(){
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
     // this.password=password;
       this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
        

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    
}
