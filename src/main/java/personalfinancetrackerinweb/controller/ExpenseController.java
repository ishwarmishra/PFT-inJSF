package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.repository.*;
import personalfinancetrackerinweb.model.Expense;

@Named
@ViewScoped
public class ExpenseController implements Serializable {

    @Inject
    private ExpenseRepository expenseRepository;

    private Expense expense;
    private List<Expense> expenseList;

    public ExpenseRepository getExpenseRepository() {
        return expenseRepository;
    }

    public void setExpenseRepository(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
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

    @PostConstruct
    public void init() {
        expense = new Expense();
        findAll();
    }

    public void beforeCreate() {
        expense = new Expense();
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
