package personalfinancetrackerinweb.repository;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import personalfinancetrackerinweb.model.Category;
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

    public List<Expense> findByCategoryAndDateRange(Category category, Date startDate, Date endDate) {
        TypedQuery<Expense> query = entityManager.createQuery(
                "SELECT e FROM Expense e WHERE e.category = :category AND e.date BETWEEN :startDate AND :endDate",
                Expense.class
        );
        query.setParameter("category", category);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }
}
