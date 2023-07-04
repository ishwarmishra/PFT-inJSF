package personalfinancetrackerinweb.repository.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import personalfinancetrackerinweb.model.AbstractEntity;

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
    @Transactional
    public void delete(int id) {
        T entity = getEntityManager().find(entityClass, id);
        if (entity != null) {
            getEntityManager().remove(entity);
        }
    }

    @Override
    @Transactional
    public T update(T data) {
        return getEntityManager().merge(data);
    }
}
