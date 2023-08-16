package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import personalfinancetrackerinweb.model.Budget;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.BudgetRepositoryImpl;
import personalfinancetrackerinweb.repository.CategoryRepositoryImpl;

@Named
@ViewScoped
public class BudgetController extends AbstractMessageController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void init() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
       .getExternalContext().getRequest();
        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");

        budget = new Budget();
        categoryList = categoryRepositoryImpl.findByCategoryType(managedUser, CategoryType.EXPENSE);
        System.out.println("");
        findAll();
    }

    public void beforeCreate() {
        budget = new Budget();
         HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        budget.setUser(managedUser);

        
    }

    public void setBudget(Budget budget) {
        this.budget = budget;

    }
    
    public void saveData() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        budget.setUser(managedUser);

        if (budget.getId() == 0) {
            budgetRepositoryImpl.create(budget);
            super.infoMessage("Budgeting done successfully!");

        } else {
            budgetRepositoryImpl.update(budget);
            super.infoMessage("Budgeting update successfully!");

        }
        budget = new Budget();
        findAll();

    }

    public void deleteData(Budget budget) {
        budgetRepositoryImpl.delete(budget.getId());
        super.warningMessage("Budgeting Delete successfully!");
        findAll();
    }

    public void findAll() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        budgetList = budgetRepositoryImpl.findByUser(managedUser.getId());
    }

    public String getHeader() {
        if (budget.getId() == 0) {
            return "Add Budget";
        } else {
            return "Update Budget";
        }
    }
}
