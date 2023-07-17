package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Named
@ViewScoped
public class BudgetController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private Budget budget;
    private List<Budget> budgetList;
    private List<Category> categoryList;

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
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void saveData() {
        if (budget.getId() == 0) {
            budgetRepositoryImpl.create(budget);
        } else {
            budgetRepositoryImpl.update(budget);
        }
        budget = new Budget();
        findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Budgeting done successfully!"));

    }

    public void deleteData(Budget budget) {
        budgetRepositoryImpl.delete(budget.getId());
        findAll();
    }

    public void updateData(Budget budgetEntity) {
        budgetRepositoryImpl.update(budgetEntity);
    }

    public void findAll() {
        budgetList = budgetRepositoryImpl.findAll();
    }
}
