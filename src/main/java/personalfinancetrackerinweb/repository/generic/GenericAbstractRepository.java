package personalfinancetrackerinweb.repository.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import personalfinancetrackerinweb.model.GenericEntityInterface;

public abstract class GenericAbstractRepository<T extends GenericEntityInterface> implements GenericRepositoryInterface<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<T> entityClass;

    public GenericAbstractRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .getResultList();
    }

    @Override
    public T findById(int id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    @Transactional
    public T create(T data) {
        entityManager.persist(data);
        return data;
    }

    @Override
    @Transactional
    public void delete(int id) {
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    @Override
    @Transactional
    public T update(T data) {
        return entityManager.merge(data);
    }
}
