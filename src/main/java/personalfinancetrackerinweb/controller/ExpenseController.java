package personalfinancetrackerinweb.controller;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.CategoryRepository;
import personalfinancetrackerinweb.repository.ExpenseRepository;
import personalfinancetrackerinweb.model.Expense;

@Named
@ViewScoped
public class ExpenseController implements Serializable {

    @Inject
    private ExpenseRepository expenseRepository;
    
    @Inject
    private CategoryRepository categoryRepository;
 
    private Expense expense;
    private List<Expense> expenseList;
    private List<Category> categoryList;

    
    public ExpenseRepository getExpenseRepository() {
        return expenseRepository;
    }

    public void setExpenseRepository(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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
    
    public void beforeCreate(){
        expense = new Expense();
        categoryList = categoryRepository.findByCategoryType(CategoryType.EXPENSE);
    }
    
    public void setExpense(Expense expense) {
        this.expense = expense;
    }
    
    public void saveData() {
        if (expense.getId() == 0) {
            expenseRepository.create(expense);
        } else {
            expenseRepository.update(expense);
        }
        expense = new Expense();
        findAll();
    }

    public void deleteData(Expense expense) {
        expenseRepository.delete(expense.getId());
        findAll();
    }

    public void updateData(Expense expenseEntity) {
        expenseRepository.update(expenseEntity);
    }
    
    public void findAll() {
        expenseList = expenseRepository.findAll();
    }
}
