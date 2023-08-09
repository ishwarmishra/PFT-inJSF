package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public User findByUsername(User user) {
        try{
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username",User.class);
        query.setParameter("username", user.getUsername());
        User result= (User) query.getSingleResult();
        return result;
        
        }catch(NoResultException e){
           return null;
        }
    }
}

