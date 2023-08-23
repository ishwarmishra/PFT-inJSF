package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.acl.ActionType;
import personalfinancetrackerinweb.acl.Permission;
import personalfinancetrackerinweb.acl.ResourceType;
import personalfinancetrackerinweb.acl.UserRole;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class AccessControlRepository extends GenericAbstractRepository<Permission>{
     
    @PersistenceContext(name = "pft")
    private EntityManager entityManager;
    
     @Override 
    protected EntityManager getEntityManager(){
    return entityManager;
    }

    public AccessControlRepository() {
        super(Permission.class);
    }
    
    public boolean permissionAllowed(UserRole userRole, ResourceType resourceType, ActionType actionType){
     try{   
      String jpql="SELECT p.allowAction FROM Permission p WHERE p.userRole= :userRole AND p.resourceType =:resourceType AND p.actionType=:actionType";
      
      Boolean allow = entityManager.createQuery(jpql, Boolean.class)
        .setParameter("userRole", userRole)
        .setParameter("resourceType", resourceType)
        .setParameter("actionType", actionType)
        .getSingleResult();

      return allow != null && allow;
        } catch (NoResultException e) {
            return false;
        }
    }
}
