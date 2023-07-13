package personalfinancetrackerinweb.repository.generic;

import java.util.List;

public interface GenericRepositoryInterface<T> {
    T create(T data);
    void delete(int id);
    T update(T data);
    T getById(int id);
    List<T> findAll();
    
    List<Object[]> getIncomesByMonth();
    List<Object[]> getExpensesByMonth();
    
    List<T> getBudgetsForCurrentMonth();
    

}