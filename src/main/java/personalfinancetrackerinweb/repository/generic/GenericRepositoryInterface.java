package personalfinancetrackerinweb.repository.generic;

import java.util.List;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;

public interface GenericRepositoryInterface<T> {
    T create(T data);
    void delete(long id);
    T update(T data);
    T getById(long id);
    List<T> findAll();
   
    List<Category> findByCategoryType(CategoryType type);
    //List<T> findByCategoryAndDateRange(Category category, Date startDate, Date endDate);
    
    
}