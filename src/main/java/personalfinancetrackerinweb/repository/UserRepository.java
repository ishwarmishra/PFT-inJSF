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
    public User findByUsernameAndPassword(User user) {
    try {
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
        query.setParameter("username", user.getUsername());
        query.setParameter("password", user.getPassword()); 
        User result = (User) query.getSingleResult();
        return result;
    } catch (NoResultException e) {
        return null;
    }
}

}

