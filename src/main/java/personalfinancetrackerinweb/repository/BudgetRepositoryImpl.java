package personalfinancetrackerinweb.repository;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
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
