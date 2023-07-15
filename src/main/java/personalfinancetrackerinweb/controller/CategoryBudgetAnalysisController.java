package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.Expense;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.ExpenseRepositoryImpl;

@Named
@ViewScoped
public class CategoryBudgetAnalysisController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Inject
    private ExpenseRepositoryImpl expenseRepositoryImpl;

    private BarChartModel barChartModel;

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

    public ExpenseRepositoryImpl getExpenseRepositoryImpl() {
        return expenseRepositoryImpl;
    }

    public void setExpenseRepositoryImpl(ExpenseRepositoryImpl expenseRepositoryImpl) {
        this.expenseRepositoryImpl = expenseRepositoryImpl;
    }

    public BarChartModel getBarChartModel() {
        return barChartModel;
    }

    @PostConstruct
    public void init() {
        createBarChartModel();
    }

    private void createBarChartModel() {
        barChartModel = new BarChartModel();

        ChartSeries budgetSeries = new ChartSeries();
        budgetSeries.setLabel("Budget");
        ChartSeries actualSeries = new ChartSeries();
        actualSeries.setLabel("Actual");

        List<Category> expenseCategories = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
        YearMonth currentYearMonth = YearMonth.now();
        LocalDate startDate = currentYearMonth.atDay(1);
        LocalDate endDate = currentYearMonth.atEndOfMonth();

        Map<Category, BigDecimal> categoryBudgetMap = calculateCategoryBudgets(expenseCategories, startDate, endDate);
        Map<Category, BigDecimal> categoryActualMap = calculateCategoryActuals(expenseCategories, startDate, endDate);

        for (Category category : expenseCategories) {
            BigDecimal budgetAmount = categoryBudgetMap.getOrDefault(category, BigDecimal.ZERO);
            BigDecimal actualAmount = categoryActualMap.getOrDefault(category, BigDecimal.ZERO);

            budgetSeries.set(category.getName(), budgetAmount);
            actualSeries.set(category.getName(), actualAmount);
        }

        barChartModel.addSeries(budgetSeries);
        barChartModel.addSeries(actualSeries);

        barChartModel.setTitle("Expense Categories Budget vs Actual");
        barChartModel.setLegendPosition("ne");
        barChartModel.setBarWidth(30);
        barChartModel.setBarPadding(10);
        barChartModel.setAnimate(true);
        barChartModel.setShowPointLabels(true);
    }

    private Map<Category, BigDecimal> calculateCategoryBudgets(List<Category> expenseCategories, LocalDate startDate, LocalDate endDate) {
        Map<Category, BigDecimal> categoryBudgetMap = new HashMap<>();

        for (Category category : expenseCategories) {
            BigDecimal totalBudgetAmount = BigDecimal.ZERO;

            Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            List<Budget> categoryBudgets = budgetRepositoryImpl.findByCategoryAndDateRange(category, startDateAsDate, endDateAsDate);

            for (Budget budget : categoryBudgets) {
                totalBudgetAmount = totalBudgetAmount.add(budget.getAmount());
            }

            categoryBudgetMap.put(category, totalBudgetAmount);
        }

        return categoryBudgetMap;
    }

    private Map<Category, BigDecimal> calculateCategoryActuals(List<Category> expenseCategories, LocalDate startDate, LocalDate endDate) {
    Map<Category, BigDecimal> categoryActualMap = new HashMap<>();

    for (Category category : expenseCategories) {
        BigDecimal totalActualAmount = BigDecimal.ZERO;

        Date startDateAsDate = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDateAsDate = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        List<Expense> categoryExpenses = expenseRepositoryImpl.findByCategoryAndDateRange(category, startDateAsDate, endDateAsDate);

        for (Expense expense : categoryExpenses) {
            totalActualAmount = totalActualAmount.add(expense.getAmount());
        }

        categoryActualMap.put(category, totalActualAmount);
    }

    return categoryActualMap;
}
}