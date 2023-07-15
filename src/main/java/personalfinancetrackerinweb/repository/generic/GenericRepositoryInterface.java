package personalfinancetrackerinweb.repository.generic;

import java.util.Date;
import java.util.List;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;

public interface GenericRepositoryInterface<T> {
    T create(T data);
    void delete(int id);
    T update(T data);
    T getById(int id);
    List<T> findAll();
    
    List<Object[]> getIncomesByMonth();
    List<Object[]> getExpensesByMonth();
    
    List<Category> findByCategoryType(CategoryType type);
    List<T> findByCategoryAndDateRange(Category category, Date startDate, Date endDate);
}