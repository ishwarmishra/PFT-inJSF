package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Named
@ViewScoped
public class FinanceSummaryController implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private Map<String, BigDecimal> summaryData;
    
    private User user;
    
    private List<Income> incomeList;

    public IncomeRepositoryImpl getIncomeRepositoryImpl() {
        return incomeRepositoryImpl;
    }

    public void setIncomeRepositoryImpl(IncomeRepositoryImpl incomeRepositoryImpl) {
        this.incomeRepositoryImpl = incomeRepositoryImpl;
    }

    public CategoryRepositoryImpl getCategoryRepositoryImpl() {
        return categoryRepositoryImpl;
    }

    public void setCategoryRepositoryImpl(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }
    
    public Map<String, BigDecimal> getSummaryData() {
        return summaryData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
    
   
    @PostConstruct
    public void init() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();
        User incomeUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        
        incomeList = incomeRepositoryImpl.findByUser(incomeUser.getId());

        calculateSummaryData();
    
    }

    private void calculateSummaryData() {
        summaryData = new HashMap<>();

        // Get all income records from the database
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();

        User incomeUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        incomeList = incomeRepositoryImpl.findByUser(incomeUser.getId());

        // Calculate total income and total expense
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Income income : incomeList) {
            CategoryType categoryType = income.getCategory().getCategoryType();
            BigDecimal amount = income.getAmount();

            if (CategoryType.INCOME.equals(categoryType)) {
                totalIncome = totalIncome.add(amount);
            } else if (CategoryType.EXPENSE.equals(categoryType)) {
                totalExpense = totalExpense.add(amount);
            }
        }
        summaryData.put("INCOME", totalIncome);
        summaryData.put("EXPENSE", totalExpense);
    }

}
