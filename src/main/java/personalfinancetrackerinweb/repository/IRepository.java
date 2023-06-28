
package personalfinancetrackerinweb.repository;

import java.util.List;


public interface IRepository<T> {
    
    T add(T data);

    void delete(int id);

    T update(T data);

    T findById(int id);

    List<T> findAll();


    
}
