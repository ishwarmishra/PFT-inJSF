
package personalfinancetrackerinweb.repository.generic;

import java.util.List;


public interface GenericRepositoryInterface<T> {
    
    T add(T data);

    void delete(int id);

    T update(T data);

    T findById(int id);

    List<T> findAll();


    
}
