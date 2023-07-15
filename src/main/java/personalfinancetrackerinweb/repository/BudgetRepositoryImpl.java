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

    public List<Budget> findByCategoryAndDateRange(Category category, Date startDate, Date endDate) {
        TypedQuery<Budget> query = entityManager.createQuery(
                "SELECT b FROM Budget b WHERE b.category = :category AND b.date BETWEEN :startDate AND :endDate",
                Budget.class
        );
        query.setParameter("category", category);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

}
