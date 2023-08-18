package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.Date;
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
import org.primefaces.event.SelectEvent;


@Named
@ViewScoped
public class BudgetController extends AbstractMessageController implements Serializable {

    @Inject
    private BudgetRepositoryImpl budgetRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private User user;

    private Category category;

    private Budget newBudget;

    private List<Budget> budgetList;

    private List<Category> categoryList;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Budget getNewBudget() {
        return newBudget;
    }

    public void setNewBudget(Budget newBudget) {
        this.newBudget = newBudget;
    }

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

        newBudget = new Budget();
        categoryList = categoryRepositoryImpl.findByCategoryType(managedUser, CategoryType.EXPENSE);
        findAll();
    }

    public void updateFromDate(SelectEvent event) {
        this.newBudget.setFromDate((Date)event.getObject());
    }
      public void updateToDate(SelectEvent event) {
        this.newBudget.setToDate((Date)event.getObject());
    }

    public void beforeCreate() {

        newBudget = new Budget();
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        newBudget.setUser(managedUser);

        categoryList = categoryRepositoryImpl.findByCategoryType(managedUser, CategoryType.EXPENSE);
    }

    public void setBudget(Budget budget) {
        this.newBudget = budget;
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        newBudget.setUser(managedUser);
    }

    public void saveData() {

        if (newBudget == null) {
            super.infoMessage("Budget is null!");
            return;
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();

        User managedUser = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        newBudget.setUser(managedUser);

        if (newBudget == null || newBudget.getCategory() == null) {
            super.infoMessage("Budget or category is null!");
            return;
        }

        if (newBudget.getId() == 0) {
            budgetRepositoryImpl.create(newBudget);
            super.infoMessage("Budgeting done successfully!");

        } else {
            budgetRepositoryImpl.update(newBudget);
            super.infoMessage("Budgeting update successfully!");
        }
        newBudget = new Budget();
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
        if (newBudget.getId() == 0) {
            return "Add Budget";
        } else {
            return "Update Budget";
        }
    }
}
