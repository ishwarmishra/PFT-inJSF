package personalfinancetrackerinweb.repository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class BudgetRepositoryImpl extends GenericAbstractRepository<Budget> {

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public BudgetRepositoryImpl() {
        super(Budget.class);
    }

}
