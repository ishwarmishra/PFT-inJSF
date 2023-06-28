package personalfinancetrackerinweb.controller;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private IncomeRepository<IncomeRepository> incomeRepository;
    
    List<IncomeEntity> incomeEntity;
    
    
    public List<IncomeEntity>getIncomeList(){
        return incomeEntity;
        
    }
    public void setIncomeList(List<IncomeEntity> incomeEntity){
        this.incomeEntity=incomeEntity;
    }
        
    @PostConstruct
    public void init() {
        incomeRepository = new IncomeRepository<>();
    }
    
    public String add(){
        incomeRepository.add((IncomeEntity) incomeEntity);
        return null;
    }
    
    public void delete(int id){
        incomeRepository.delete(id);
    }
    
    public String update(int id){
        this.incomeEntity=incomeEntity;
        incomeRepository.update((IncomeEntity) incomeEntity);
        return null;
    }
       
       
}


