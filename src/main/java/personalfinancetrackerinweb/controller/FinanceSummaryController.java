package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.Income;
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

    @PostConstruct
    public void init() {
        calculateSummaryData();
    }

    private void calculateSummaryData() {
        summaryData = new HashMap<>();

        // Get all income records from the database
        List<Income> incomeList = incomeRepositoryImpl.findAll();

        // Calculate total income and total expense
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Income income : incomeList) {
            CategoryType categoryType = income.getCategory().getType();
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
