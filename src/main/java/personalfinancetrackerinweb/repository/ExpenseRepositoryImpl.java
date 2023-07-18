package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.Expense;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class ExpenseRepositoryImpl extends GenericAbstractRepository<Expense>  {

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public ExpenseRepositoryImpl() {
        super(Expense.class);
   
    }
}
