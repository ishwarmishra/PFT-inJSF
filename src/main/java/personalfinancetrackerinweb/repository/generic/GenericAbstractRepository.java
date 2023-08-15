package personalfinancetrackerinweb.repository.generic;

import java.util.List;
import javax.persistence.EntityManager;
import personalfinancetrackerinweb.model.AbstractEntity;

public abstract class GenericAbstractRepository<T extends AbstractEntity> implements GenericRepositoryInterface<T> {

    protected abstract EntityManager getEntityManager();

    private Class<T> entityClass;

    public GenericAbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

   @Override
    public List<T> findByUser(long userId) {
        EntityManager entityManager = getEntityManager(); // Obtain the EntityManager here
        return entityManager.createQuery(
                "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.user.id = :userId", entityClass)
                .setParameter("userId", userId)
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
    
}
