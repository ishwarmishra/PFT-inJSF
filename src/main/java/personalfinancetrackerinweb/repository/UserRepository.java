package personalfinancetrackerinweb.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;


@Stateless
public class UserRepository extends  GenericAbstractRepository<User>{

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;
    
    @Override 
    protected EntityManager getEntityManager(){
    return entityManager;
    }
    
    public UserRepository (){
    super(User.class);
    }    
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class
        ).setParameter("username", username);       
        List<User> userList = query.getResultList();
        return userList.isEmpty() ? null : userList.get(0);
    }
    public List<String> getUserList() {
    List<String> result = null;
    try {
        Query query = entityManager.createQuery("SELECT u.username FROM User u ", String.class);
        result = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}
}


