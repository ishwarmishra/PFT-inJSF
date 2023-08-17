package personalfinancetrackerinweb.repository.generic;

import java.util.List;

public interface GenericRepositoryInterface<T> {
    T create(T data);
    void delete(long id);
    T update(T data);
    T getById(long id);
    
    List<T> findByUser(long id);
       
}