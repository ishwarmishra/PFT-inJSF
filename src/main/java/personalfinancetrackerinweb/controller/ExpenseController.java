package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import personalfinancetrackerinweb.repository.ExpenseRepository;
import personalfinancetrackerinweb.model.ExpenseEntity;

@Named
@ViewScoped
public class ExpenseController implements Serializable {

    @Inject
    private ExpenseRepository expenseRepository;

    private ExpenseEntity expense;
    private List<ExpenseEntity> expenseList;

    public ExpenseRepository getExpenseRepository() {
        return expenseRepository;
    }

    public void setExpenseRepository(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseEntity getExpense() {
        return expense;
    }

    public void setExpense(ExpenseEntity expense) {
        this.expense = expense;
    }

    public List<ExpenseEntity> getExpenseList() {
        return expenseList;
    }

    public void setExpenseList(List<ExpenseEntity> expenseList) {
        this.expenseList = expenseList;
    }

    @PostConstruct
    public void init() {
        expense = new ExpenseEntity();
        findAll();
    }

    public void saveData() {
        expenseRepository.create(expense);
        expense = new ExpenseEntity();
        findAll();
    }

    public void deleteData(int id) {
        expenseRepository.delete(id);
        findAll();
    }

    public void updateData(ExpenseEntity expenseEntity) {
        expenseRepository.update(expenseEntity);
        findAll();
    }

    public void findAll() {
        expenseList = expenseRepository.findAll();
    }

    public void findById(int id) {
        expense = expenseRepository.findById(id);
    }
}
