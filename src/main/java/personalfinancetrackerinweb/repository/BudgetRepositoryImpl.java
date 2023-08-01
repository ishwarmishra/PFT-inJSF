package personalfinancetrackerinweb.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
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
 
  public Map<Category, BigDecimal> budgetAmountList() {
    try {
        Query query = entityManager.createQuery("SELECT b FROM Budget b", Budget.class);
        List<Budget> resultList = query.getResultList();

        Map<Category, BigDecimal> budgetAmountMap = new HashMap<>();
        for (Budget budget : resultList) {
            budgetAmountMap.put(budget.getCategory(), budget.getAmount());
        }
        return budgetAmountMap;
    } catch (Exception e) {
        e.printStackTrace(); // For demonstration purposes, you can print the stack trace
        return new HashMap<>(); // Return an empty map or handle the exception case as required
    }
 }
}



// public List<Object[]> budgetCategoryAmount(){
//        TypedQuery<Object[]> query=entityManager.createQuery("SELECT b.amount, b.category FROM Budget b",Object[].class);
//        List<Object[]> result=query.getResultList();
//        return result;        
//    }


//private List<Object[]> stockBudget;
//
//    public List<Object[]> getStockBudget() {
//        stockBudget = budgetRepositoryImpl.budgetCategoryAmount();
//        return stockBudget;
//    }
//
//    public void setStockBudget(List<Object[]> stockBudget) {
//        this.stockBudget = stockBudget;
//    }
