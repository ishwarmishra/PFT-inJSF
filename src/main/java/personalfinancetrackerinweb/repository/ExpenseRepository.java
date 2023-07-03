package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.ExpenseEntity;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class ExpenseRepository extends GenericAbstractRepository<ExpenseEntity> {

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public ExpenseRepository() {
        super(ExpenseEntity.class);
    }
}
