package personalfinancetrackerinweb.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.BudgetRepository;
import personalfinancetrackerinweb.repository.CategoryRepository;

@Named
@ViewScoped
public class BudgetController implements Serializable {

    @Inject
    private BudgetRepository budgetRepository;

    @Inject
    private CategoryRepository categoryRepository;

    private Budget budget;
    private List<Budget> budgetList;
    private List<Category> categoryList;

    public BudgetRepository getBudgetRepository() {
        return budgetRepository;
    }

    public void setBudgetRepository(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Budget getBudget() {
        return budget;
    }

    public List<Budget> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<Budget> budgetList) {
        this.budgetList = budgetList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @PostConstruct
    public void init() {
        budget = new Budget();
        findAll();
    }

    public void beforeCreate() {
        budget = new Budget();
        categoryList = categoryRepository.findByCategoryType(CategoryType.EXPENSE);
    }
    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void saveData() {
        if (budget.getId() == 0) {
            budgetRepository.create(budget);
        } else {
            budgetRepository.update(budget);
        }
        budget = new Budget();
        findAll();
    }

    public void deleteData(Budget budget) {
        budgetRepository.delete(budget.getId());
        findAll();
    }

    public void updateData(Budget budgetEntity) {
        budgetRepository.update(budgetEntity);
    }

    public void findAll() {
        budgetList = budgetRepository.findAll();
    }
}
