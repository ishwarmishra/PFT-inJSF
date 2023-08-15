package personalfinancetrackerinweb.repository;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class IncomeRepositoryImpl extends GenericAbstractRepository<Income> {

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public IncomeRepositoryImpl() {
        super(Income.class);
    }

    public Income find(long id) {
        return entityManager.find(Income.class, id);
    }

    //Calculate the amount of the each category inside the Income Model within date range
    public BigDecimal findBudgetOfCategoryBetweenDate(User user, Income incomeCategory, Date fromDate, Date toDate) {

        try {
            Query query = entityManager.createQuery("SELECT SUM(s.amount) FROM Income s WHERE s.date BETWEEN :fromDate AND :toDate AND s.user=:user s.category=:category GROUP BY s.category", BigDecimal.class);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);
            query.setParameter("user",user);

            query.setParameter("category", incomeCategory.getCategory());
            BigDecimal result = (BigDecimal) query.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new BigDecimal("0");
    }

    //Calculate the name,amount of the expense category with in a date range inside the Income Model
    public List<Object[]> calculateActualExpense(User user,Date fromDate, Date toDate) {
       List<Object[]> result = null;
       try {
        Query query = entityManager.createQuery("SELECT i.amount, i.category  FROM Income i WHERE i.date BETWEEN :fromDate AND :toDate AND i.user = :user",Object[].class );       
        query.setParameter("fromDate", fromDate);
        query.setParameter("toDate", toDate);
        query.setParameter("user",user);
        result = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
   }

}
