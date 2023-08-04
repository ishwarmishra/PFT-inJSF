package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.Login;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;



@Stateless
public  class LoginRepositoryImpl extends GenericAbstractRepository<Login>{
    
    @PersistenceContext(name = "pft")
    private EntityManager entityManager;


    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

   
    public LoginRepositoryImpl() {
        super(Login.class);
        
        
    }
    
}


