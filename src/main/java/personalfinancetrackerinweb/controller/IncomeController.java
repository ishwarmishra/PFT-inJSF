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
import personalfinancetrackerinweb.repository.*;

import personalfinancetrackerinweb.model.Income;

@Named
@ViewScoped
public class IncomeController implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;
    
    //Make the Income class instance
    private Income income;
    
    //Use to strore all income  and expense records retrieved from the DATABASE
    private List<Income> incomeList;
    
    //Use to store all category records retrieved from the DATABASE
    private List<Category> categoryList;

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
    
    //To get the user Input from the dialog box
    public Income getIncome() {
        return income;
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

    @PostConstruct
    public void init() {
        //create an instance of the Income
        income = new Income();
        
        //Use to retrieves all the income and expense categories from the DATABASE and populates the categoryList
        categoryList = categoryRepositoryImpl.findAll();
        
        //To fetch all income and expense records and populates the incomeList
        findAll();
    }
    //create a new income records,it resets the income instance and fetches the category list again
    public void beforeCreate() {
        income = new Income();
        categoryList = categoryRepositoryImpl.findAll();
    }
    //Used when editing existing records
    public void setIncome(Income income) {
        this.income = income;

    }
    //Used to save the data into databases
    public void saveData() {
        if (income.getId() == 0) {
            incomeRepositoryImpl.create(income);
        } else {
            incomeRepositoryImpl.update(income);
        }
        income = new Income();
        findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Expenditure saved successfully!"));
    }
    //Used to delete the data from database
    public void deleteData(Income income) {
        incomeRepositoryImpl.delete(income.getId());
        findAll();
    }

    //Used to fetches the All Income and Expense Records from the database
    public void findAll() {
        incomeList = incomeRepositoryImpl.findAll();

    }
}
