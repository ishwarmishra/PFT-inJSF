package personalfinancetrackerinweb.repository.generic;

import java.util.List;
import javax.persistence.EntityManager;
import personalfinancetrackerinweb.model.GenericEntityInterface;

public abstract class GenericAbstractRepository<T extends GenericEntityInterface> implements GenericRepositoryInterface<T> {
    protected abstract EntityManager getEntitiManager();
    
    private Class<T> entityClass;

    public GenericAbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public  List<T> findAll(){
    return null;
    };

    @Override
    public T findById(int id){
    return null;
    }

    @Override
    public T add(T data) {
        System.out.println(getEntitiManager());
        getEntitiManager().persist(data);
        getEntitiManager().flush();
        return data;
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public T update(T data) {
    return  null;
    }


    
    
    
    
    
}
