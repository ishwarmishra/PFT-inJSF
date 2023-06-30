package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.repository.*;

import personalfinancetrackerinweb.model.IncomeEntity;

@Named
@ViewScoped
public class IncomeController implements Serializable {

    @Inject
    private IncomeRepository incomeRepository;

    private IncomeEntity income;
    private List<IncomeEntity> incomeList;

    public IncomeRepository getIncomeRepository() {
        return incomeRepository;
    }

    public void setIncomeRepository(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    public IncomeEntity getIncome() {
        return income;
    }

    public void setIncome(IncomeEntity income) {
        this.income = income;
    }

    public List<IncomeEntity> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<IncomeEntity> incomeList) {
        this.incomeList = incomeList;
    }

    @PostConstruct
    public void init() {
        income = new IncomeEntity();
        findAll();
    }

    public void saveData() {

        incomeRepository.create(income);
        income = new IncomeEntity();
        findAll();
    }

    public void deleteData(int id) {
        incomeRepository.delete(id);
        findAll();
    }

    public void updateData(IncomeEntity incomeEntity) {
        incomeRepository.update(incomeEntity);
        findAll();
    }

    public void findAll() {
        incomeList = incomeRepository.findAll();
    }

    public void findById(int id) {
        income = incomeRepository.findById(id);
    }
}
