package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.model.IncomeEntity;
import personalfinancetrackerinweb.repository.IncomeRepository;

@Named
@ViewScoped
public class IncomeController implements Serializable {

    @Inject
    private IncomeRepository incomeRepository;

    private IncomeEntity income;

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

    public List<IncomeEntity> getIncomeEntity() {
        return incomeEntity;
    }

    public void setIncomeEntity(List<IncomeEntity> incomeEntity) {
        this.incomeEntity = incomeEntity;
    }

    List<IncomeEntity> incomeEntity;

    public List<IncomeEntity> getIncomeList() {
        return incomeEntity;

    }

    public void setIncomeList(List<IncomeEntity> incomeEntity) {
        this.incomeEntity = incomeEntity;
    }

    @PostConstruct
    public void init() {
        income = new IncomeEntity();
    }

    public void saveData() {
        incomeRepository.add(income);
        income=new IncomeEntity();
    }
}
