package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.*;

import personalfinancetrackerinweb.model.Income;

@Named
@ViewScoped
public class IncomeController implements Serializable {

    @Inject
    private IncomeRepositoryImpl incomeRepositoryImpl;

    @Inject
    private CategoryRepositoryImpl categoryRepositoryImpl;

    private Income income;
    private List<Income> incomeList;
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

    public void setCategoryRepositoryImpl(CategoryRepositoryImpl categoryRepositoryImpl ){
        this.categoryRepositoryImpl = categoryRepositoryImpl;
    }

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
        income = new Income();
        findAll();
    }

    public void beforeCreate() {
        income = new Income();
        categoryList = categoryRepositoryImpl.findByCategoryType(CategoryType.INCOME);
    }

    public void setIncome(Income income) {
        this.income = income;
    }

    public void saveData() {
        if (income.getId() == 0) {
            incomeRepositoryImpl.create(income);
        } else {
            incomeRepositoryImpl.update(income);
        }
        income = new Income();
        findAll();
    }

    public void deleteData(Income income) {
        incomeRepositoryImpl.delete(income.getId());
        findAll();
    }

    public void updateData(Income incomeEntity) {
        incomeRepositoryImpl.update(incomeEntity);
    }

    public void findAll() {
        incomeList = incomeRepositoryImpl.findAll();

    }

}
