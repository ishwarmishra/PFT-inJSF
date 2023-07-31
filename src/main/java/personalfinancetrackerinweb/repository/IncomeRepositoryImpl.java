package personalfinancetrackerinweb.repository;

import java.math.BigDecimal;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import personalfinancetrackerinweb.model.Income;
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

//   public BigDecimal findBudgetOfCategoryBetweenDate(Income incomeCategory, Date fromDate, Date toDate) {
//      
//    try{
//    Query query = entityManager.createQuery("SELECT SUM(s.amount) FROM Income s WHERE s.category=:category group by s.category",BigDecimal.class);          
//    query.setParameter("category", incomeCategory.getCategory());
//    BigDecimal result=(BigDecimal) query.getSingleResult();
//    return result;
//    }
//    catch(Exception e){
//        System.out.println(e.getMessage());    }
//    return  new BigDecimal ("0");
//  }
//}
    public BigDecimal findBudgetOfCategoryBetweenDate(Income incomeCategory, Date fromDate, Date toDate) {

        try {
            Query query = entityManager.createQuery("SELECT SUM(s.amount) FROM Income s WHERE s.date BETWEEN :fromDate AND :toDate AND s.category=:category GROUP BY s.category", BigDecimal.class);
            query.setParameter("fromDate", fromDate);
            query.setParameter("toDate", toDate);

            query.setParameter("category", incomeCategory.getCategory());
            BigDecimal result = (BigDecimal) query.getSingleResult();
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new BigDecimal("0");
    }
}
