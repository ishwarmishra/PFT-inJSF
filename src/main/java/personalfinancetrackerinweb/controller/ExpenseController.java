package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.Expense;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;
import personalfinancetrackerinweb.repository.ExpenseRepositoryImpl;

@Named
@ViewScoped
public class ExpenseController implements Serializable {

    @Inject
    private ExpenseRepositoryImpl expenseRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private Expense expense;
    private List<Expense> expenseList;
    private List<Category> categoryList;

    public ExpenseRepositoryImpl getExpenseRepositoryImpl() {
        return expenseRepositoryImpl;
    }

    public void setExpenseRepositoryImpl(ExpenseRepositoryImpl expenseRepositoryImpl) {
        this.expenseRepositoryImpl = expenseRepositoryImpl;
    }

    public CategoryRepositoryImpl getCategoryRepositoryImpl() {
        return categoryRepositoryImpl;
    }

    public void setCategoryRepository(CategoryRepositoryImpl categoryRepositoryImpl) {
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }

    public Expense getExpense() {
        return expense;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<Expense> expenseList) {
        this.expenseList = expenseList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @PostConstruct
    public void init() {
        expense = new Expense();
        findAll();
    }

    public void beforeCreate() {
        expense = new Expense();
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.EXPENSE);
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public void saveData() {
        if (expense.getId() == 0) {
            expenseRepositoryImpl.create(expense);
        } else {
            expenseRepositoryImpl.update(expense);
        }
        expense = new Expense();
        findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Expense saved successfully!"));

    }

    public void deleteData(Expense expense) {
        expenseRepositoryImpl.delete(expense.getId());
        findAll();
    }

    public void updateData(Expense expenseEntity) {
        expenseRepositoryImpl.update(expenseEntity);
    }

    public void findAll() {
        expenseList = expenseRepositoryImpl.findAll();
    }
}