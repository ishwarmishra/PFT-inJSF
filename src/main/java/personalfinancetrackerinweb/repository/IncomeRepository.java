package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.IncomeEntity;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class IncomeRepository extends GenericAbstractRepository<IncomeEntity> {

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public IncomeRepository() {
        super(IncomeEntity.class);
    }

}
