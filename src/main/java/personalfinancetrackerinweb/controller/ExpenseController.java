package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import personalfinancetrackerinweb.repository.ExpenseRepository;
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

    public void setExpense(Expense expense) {
        this.expense = expense;
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
        expenseList = expenseRepository.findAll();

    }

    public void saveData() {
        expenseRepository.create(expense);
        expense = new Expense();
    }

    public void deleteData(int id) {
        expenseRepository.delete(id);

    }

    public void updateData(Expense expenseEntity) {
        expenseRepository.update(expenseEntity);
    }

    
}
