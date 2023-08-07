package personalfinancetrackerinweb.repository.generic;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import personalfinancetrackerinweb.model.AbstractEntity;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;

public abstract class GenericAbstractRepository<T extends AbstractEntity> implements GenericRepositoryInterface<T> {

    protected abstract EntityManager getEntityManager();

    private Class<T> entityClass;

    public GenericAbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        return (List<T>) getEntityManager()
                .createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public T create(T data) {

        getEntityManager().persist(data);
        return data;
    }

    @Override
    public void delete(long id) {
        T entity = getEntityManager().find(entityClass, id);
        if (entity != null) {
            getEntityManager().remove(entity);
            getEntityManager().flush();
        }
    }

    @Override
    public T update(T data) {
         getEntityManager().merge(data);
         getEntityManager().flush();
         return data;
       
    }

    @Override
    public T getById(long id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<Category> findByCategoryType(CategoryType type) {
        TypedQuery<Category> query = getEntityManager().createQuery("SELECT c FROM Category c WHERE c.type = :type", Category.class);
        query.setParameter("type", type);
        return query.getResultList();
    }   
}
