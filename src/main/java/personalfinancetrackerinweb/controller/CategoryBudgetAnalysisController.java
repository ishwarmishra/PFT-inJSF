package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.IncomeRepositoryImpl;

@Named
@ViewScoped
public class CategoryBudgetAnalysisController implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    private List<Budget> budgetsList;

    private List<Category> categoryList;

    private BarChartModel barChartModel;

    private Date fromDate;
    private Date toDate;

    private Income income;

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

    public List<Budget> getBudgetsList() {
        return budgetsList;
    }

    public void setBudgetsList(List<Budget> budgetsList) {
        this.budgetsList = budgetsList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    public void setBarChartModel(BarChartModel barChartModel) {
        this.barChartModel = barChartModel;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    @PostConstruct
    public void init() {
        budgetsList = budgetRepositoryImpl.findAll();
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
        fromDate = getDefaultFromDate();
        toDate = getDefaultToDate();

        stockBudget = budgetRepositoryImpl.calculateCategoryBudgets(fromDate, toDate);
        stockBudgetActual = incomeRepositoryImpl.calculateActualExpense(fromDate, toDate);

        createBarChartModel();
    }
    // For storing the budgete amount of the expense Category with in a month
    private List<Object[]> stockBudget;

    public List<Object[]> getStockBudget() {
        stockBudget = budgetRepositoryImpl.calculateCategoryBudgets(fromDate, toDate);
        return stockBudget;
    }

    public void setStockBudget(List<Object[]> stockBudget) {
        this.stockBudget = stockBudget;
    }

    //For storing the actual expense amount within a month
    private List<Object[]> stockBudgetActual;

    public List<Object[]> getStockBudgetActual() {
        stockBudgetActual = incomeRepositoryImpl.calculateActualExpense(fromDate, toDate);
        return stockBudgetActual;
    }

    public void setStockBudgetActual(List<Object[]> stockBudgetActual) {
        this.stockBudgetActual = stockBudgetActual;
    }

    private void createBarChartModel() {
        barChartModel = new BarChartModel();

        ChartSeries budgetSeries = new ChartSeries();
        budgetSeries.setLabel("Budget");
        ChartSeries actualSeries = new ChartSeries();
        actualSeries.setLabel("Actual");

        // Populate the budgetSeries with data from stockBudget
        for (Object[] budgetData : stockBudget) {
            BigDecimal amount = (BigDecimal) budgetData[0];
            Category category = (Category) budgetData[1];
            budgetSeries.set(category.getName(), amount);
        }

        // Populate the actualSeries with data from stockBudgetActual
        for (Object[] budgetActualData : stockBudgetActual) {
            BigDecimal actualAmount = (BigDecimal) budgetActualData[0];
            Category actualCategory = (Category) budgetActualData[1];
            if (actualCategory.getType() == CategoryType.EXPENSE) {
                actualSeries.set(actualCategory.getName(), actualAmount);
            }
        }

        // Add the budgetSeries and actualSeries to the barChartModel
        barChartModel.addSeries(budgetSeries);
        barChartModel.addSeries(actualSeries);

        barChartModel.setTitle("Expense Categories Budget vs Actual");
        barChartModel.setLegendPosition("ne");
        barChartModel.setBarWidth(30);
        barChartModel.setBarPadding(10);
        barChartModel.setAnimate(true);
        barChartModel.setShowPointLabels(true);
    }

    private Date getDefaultFromDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    private Date getDefaultToDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

}
