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
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.*;

import personalfinancetrackerinweb.model.Income;

@Named
@ViewScoped
public class IncomeController extends AbstractMessageController implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    private Income income;

    private Budget budget;

    private Category category;

    private List<Income> incomeList;

    private List<Category> categoryList;

    private CategoryType ct = CategoryType.INCOME;

    private List<Budget> budgetList;

    private Date currentDate;

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

    public BudgetRepositoryImpl getBudgetRepositoryImpl() {
        return budgetRepositoryImpl;
    }

    public void setBudgetRepositoryImpl(BudgetRepositoryImpl budgetRepositoryImpl) {
        this.budgetRepositoryImpl = budgetRepositoryImpl;
    }

    public Income getIncome() {
        return income;
    }

    public void setIncome(Income income) {
        this.income = income;

    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public CategoryType getCt() {
        return ct;
    }

    public void setCt(CategoryType ct) {
        this.ct = ct;
    }

    public List<Budget> getBudgetList() {
        return budgetList;
    }

    public void setBudgetList(List<Budget> budgetList) {
        this.budgetList = budgetList;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    @PostConstruct
    public void init() {
        income = new Income();
        budget = new Budget();
        budgetList = budgetRepositoryImpl.findAll();
        categoryList = categoryRepositoryImpl.findAll();
        findAll();
        currentDate = new Date();
    }

    public String getCategoryStyle(Income item){
        if(item.getCategory().getCategoryType()==CategoryType.EXPENSE){
            return "RED";
        }
        else{
            return "GREEN";
        }
    }
    //To add the new income records,set ct (category Type) based on the type of record and load appropriate category list from the database
    public void beforeCreateIncome() {
        this.ct = CategoryType.INCOME;
        loadCategory();
    }

    public void beforeCreateExpense() {
        this.ct = CategoryType.EXPENSE;
        loadCategory();
    }

    public void beforeEditExpense(Income income) {
        this.income = income;                 //currently selected income or expense item
        ct = income.getCategory().getCategoryType();  //fetch the correct categorylist for the income or expense
        loadCategory();                       //It retrieve the correct category list for the income and expense from previuos fetched categorylist
    }

    public void saveData() {
        if (income == null || income.getCategory() == null) {
           super.infoMessage("Income or category is null!");
            return;
        }

        if (income.getId() == 0) {
            if (income.getCategory().getCategoryType().equals(CategoryType.EXPENSE)) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                Date fromDate = calendar.getTime();
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
                Date toDate = calendar.getTime();

                BigDecimal budgetAmount = budgetRepositoryImpl.getTotalBudgetAmount(income, fromDate, toDate);

                if (income.getAmount().compareTo(budgetAmount) > 0) {
                    super.warningMessage("Adding this expense exceeds the budgeted Amount very Clearly!");
                    return;
                }

                BigDecimal expenseAmount = incomeRepositoryImpl.findBudgetOfCategoryBetweenDate(income, fromDate, toDate);
                BigDecimal totalExpenseAmount = expenseAmount.add(income.getAmount());

                if (totalExpenseAmount.compareTo(budgetAmount) > 0) {
                    super.warningMessage("Adding this expense exceeds the budget!");
                    return;
                } else if (income.getId() == 0) {
                    incomeRepositoryImpl.create(income);
                    income = new Income();
                    findAll();
                    super.infoMessage("Expense Source Added Successfull!Thankyou ");
                }

            } else {
                incomeRepositoryImpl.create(income);
                income = new Income();
                findAll();
                super.infoMessage("Income Source Added Successfull!Thankyou ");
            }
        } //For Updation of the Income and EXpense Data
        else {
            if (income.getCategory().getCategoryType().equals(CategoryType.EXPENSE)) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(currentDate);
                calendar.set(Calendar.DAY_OF_MONTH, 1); // Set the day of the month to the first day
                Date fromDate = calendar.getTime();
                calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Set the day of the month to the last day
                Date toDate = calendar.getTime();

                BigDecimal budgetAmount = budgetRepositoryImpl.getTotalBudgetAmount(income, fromDate, toDate);

                if (income.getAmount().compareTo(budgetAmount) > 0) {
                    super.warningMessage("Updating this expense exceeds the budgeted Amount very Clearly!");
                    income = new Income();
                    findAll();
                    return;
                }

                BigDecimal expenseAmount = incomeRepositoryImpl.findBudgetOfCategoryBetweenDate(income, fromDate, toDate);
                BigDecimal orgValue = (incomeRepositoryImpl.getById(income.getId()).getAmount());
                
                BigDecimal totalExpenseAmount = expenseAmount.subtract(orgValue);
                
                BigDecimal realExpenseAMount = totalExpenseAmount.add(income.getAmount());
                
                        
                if (realExpenseAMount.compareTo(budgetAmount) > 0) {
                    super.warningMessage("Updatig this expense exceeds the budget!");
                    income = new Income();
                    findAll();
                    return;
                } else {
                    incomeRepositoryImpl.update(income);
                    income = new Income();
                    findAll();
                    super.infoMessage("Expense Updated successfully!");
                }
            } else {
                incomeRepositoryImpl.update(income);
                income = new Income();
                findAll();
                super.infoMessage("Income updated successfully!");
            }

        }
    }

    public void deleteData(Income income) {
        incomeRepositoryImpl.delete(income.getId());//In AbstractGeneric Method
        income = new Income();
        findAll();//After deletion new records of the income or expense is populates to 'incomeList'  
        super.warningMessage("Data deleted successfully!");
        return;
        
    }

    public void findAll() {
        incomeList = incomeRepositoryImpl.findAll();
    }

    public void loadCategory() {
        categoryList = categoryRepositoryImpl.findByCategoryType(ct);
    }

    public String getHeader() {
        if (income.getId() == 0 && ct.equals(CategoryType.INCOME)) {
            return "Add Income";

        } else if (income.getId() == 0 && ct.equals(CategoryType.EXPENSE)) {
            return "Add Expense";
        } else if (income.getId() != 0 && ct.equals(CategoryType.INCOME)) {
            return "Update Income";
        } else {
            return "Update Expense";
        }
    }
}
