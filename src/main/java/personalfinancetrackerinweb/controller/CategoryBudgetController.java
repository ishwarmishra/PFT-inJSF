//For the Pie chart Model of the Categories
package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    private Date currentDate;

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

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
    
    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }



    @PostConstruct
    public void init() {
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
        currentDate = new Date();
        createPieChartModel();
    }

    private void createPieChartModel() {
        pieChartModel = new PieChartModel();

        List<Category> expenseCategories = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.DAY_OF_MONTH, 1); 
        Date fromDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Set the day of the month to the last day
        Date toDate = calendar.getTime();
        Map<Category, BigDecimal> budgetAmountMap = budgetRepositoryImpl.budgetAmountList();
        for (Category category : expenseCategories) {
            BigDecimal budgetAmount = budgetAmountMap.getOrDefault(category, BigDecimal.ZERO);
            pieChartModel.set(category.getName(), budgetAmount);
        }
        pieChartModel.setTitle("Expense Categories vs Budget");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setShowDataLabels(true);
    }

}
