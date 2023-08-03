package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;
import personalfinancetrackerinweb.model.Budget;

import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;

@Named
@ViewScoped
public class CategoryBudgetController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    private PieChartModel pieChartModel;

    private List<Category> categoryList;

    private List<Budget> budgetList;

    private Budget budget;

    public BudgetRepositoryImpl getBudgetRepositoryImpl() {
        return budgetRepositoryImpl;
    }

    public void setBudgetRepositoryImpl(BudgetRepositoryImpl budgetRepositoryImpl) {
        this.budgetRepositoryImpl = budgetRepositoryImpl;
    }

    public CategoryRepositoryImpl getCategoryRepositoryImpl() {
        return categoryRepositoryImpl;
    }

    public void setCategoryRepositoryImpl(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }

    public IncomeRepositoryImpl getIncomeRepositoryImpl() {
        return incomeRepositoryImpl;
    }

    public void setIncomeRepositoryImpl(IncomeRepositoryImpl incomeRepositoryImpl) {
        this.incomeRepositoryImpl = incomeRepositoryImpl;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Budget> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<Budget> budgetList) {
        this.budgetList = budgetList;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }
    //For TYPED QUERY STORED IN THE BudgetRepositoryImpl
    private List<Object[]> stockBudget;

    public List<Object[]> getStockBudget() {
        stockBudget = budgetRepositoryImpl.budgetCategoryAmount();
        return stockBudget;
    }

    public void setStockBudget(List<Object[]> stockBudget) {
        this.stockBudget = stockBudget;
    }

    @PostConstruct
    public void init() {
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
        stockBudget = budgetRepositoryImpl.budgetCategoryAmount();
        createPieChartModel();
    }

    private void createPieChartModel() {

        pieChartModel = new PieChartModel();

        // Get the total budget amount for all expense categories
        BigDecimal totalExpenseBudget = BigDecimal.ZERO;
        for (Object[] budgetData : stockBudget) {
            totalExpenseBudget = totalExpenseBudget.add((BigDecimal) budgetData[0]);

        }
        // Add data to the pie chart model

        for (Object[] budgetData : stockBudget) {
            BigDecimal amount = (BigDecimal) budgetData[0];
            Category category = (Category) budgetData[1];
           
            // Calculate the percentage of the category amount compared to the total expense budget
            BigDecimal percentage = amount
                    .divide(totalExpenseBudget, 2, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100));
            pieChartModel.set(category.getName(), percentage);
        }

        pieChartModel.setTitle("Expense Category Budget Distribution");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setShowDataLabels(true);
    }

}
