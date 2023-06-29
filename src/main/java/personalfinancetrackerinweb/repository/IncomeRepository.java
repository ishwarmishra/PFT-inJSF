package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;
import personalfinancetrackerinweb.model.IncomeEntity;

@Stateless
public class IncomeRepository extends GenericAbstractRepository<IncomeEntity> {

    @PersistenceContext(name = "pft")
    private  EntityManager entityManager;

    
   
    @Override
    protected EntityManager getEntitiManager() {
        return entityManager;
    }

    public IncomeRepository() {
        super(IncomeEntity.class);
    }
}
