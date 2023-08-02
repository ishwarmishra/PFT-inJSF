package personalfinancetrackerinweb.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Income;
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

    public BigDecimal getTotalBudgetAmount(Income income, Date fromDate, Date toDate) {
    try{
        Query query = entityManager.createQuery("Select b from Budget b where  b.category=:category AND b.toDate >=:toDate", Budget.class);
        query.setParameter("toDate", new Date());
        query.setParameter("category", income.getCategory());
        Budget results = (Budget) query.getSingleResult();
        BigDecimal result = results.getAmount();
        return result;
    }
    catch(Exception e){
    }
   return  new BigDecimal("0");
   
    }
    
    public List<Object[]> budgetCategoryAmount() {
    List<Object[]> result = null;
    try {
        TypedQuery<Object[]> query = entityManager.createQuery("SELECT b.amount, b.category FROM Budget b", Object[].class);
        result = query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        
    }
    return result;
   }
    
  
}



