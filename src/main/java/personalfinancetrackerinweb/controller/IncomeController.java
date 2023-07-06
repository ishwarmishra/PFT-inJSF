package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import personalfinancetrackerinweb.repository.*;

import personalfinancetrackerinweb.model.Income;

@Named
@ViewScoped
public class IncomeController implements Serializable {

    @Inject
    private IncomeRepository incomeRepository;

    private Income income;
    private List<Income> incomeList;

    public IncomeRepository getIncomeRepository() {
        return incomeRepository;
    }

    public void setIncomeRepository(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
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

    @PostConstruct
    public void init() {
        income = new Income();
       findAll();
    }
    public void beforeCreate(){
        income=new Income();
    }

    
     public void setIncome(Income income) {
        this.income = income;
    }
    //TODO
    
    public void saveData() {
        if (income.getId() == 0) {
            incomeRepository.create(income);
        } else {
            incomeRepository.update(income);
        }
        income = new Income();
        findAll();
    }

    public void deleteData(Income income) {
        incomeRepository.delete(income.getId());
        findAll();
    }

    public void updateData(Income incomeEntity) {
        incomeRepository.update(incomeEntity);
    }
    
    public void findAll(){
      incomeList = incomeRepository.findAll();

    }

}
