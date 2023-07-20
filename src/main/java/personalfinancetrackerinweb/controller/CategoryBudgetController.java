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

import org.primefaces.model.chart.PieChartModel;

import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Named
@ViewScoped
public class CategoryBudgetController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private PieChartModel pieChartModel;

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

    public PieChartModel getPieChartModel() {
        return pieChartModel;
    }

    @PostConstruct
    public void init() {
        
        createPieChartModel();
    }

    private void createPieChartModel() {
        pieChartModel = new PieChartModel();

        List<Category> expenseCategories = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
        YearMonth currentYearMonth = YearMonth.now();
        LocalDate startDate = currentYearMonth.atDay(1);
        LocalDate endDate = currentYearMonth.atEndOfMonth();

        Map<Category, BigDecimal> categoryBudgetMap = calculateCategoryBudgets(expenseCategories, startDate, endDate);

        for (Category category : expenseCategories) {
            BigDecimal budgetAmount = categoryBudgetMap.getOrDefault(category, BigDecimal.ZERO);
            pieChartModel.set(category.getName(), budgetAmount);
        }

        pieChartModel.setTitle("Expense Categories vs Budget");
        pieChartModel.setLegendPosition("e");
        pieChartModel.setShowDataLabels(true);
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
}

