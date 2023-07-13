package personalfinancetrackerinweb.repository.generic;

import java.time.LocalDate;
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

    public void delete(int id) {
        T entity = getEntityManager().find(entityClass, id);
        if (entity != null) {
            getEntityManager().remove(entity);
            getEntityManager().flush();
        }
    }

    @Override

    public T update(T data) {
        return getEntityManager().merge(data);
    }

    @Override
    public T getById(int id) {
        return getEntityManager().find(entityClass, id);

    }

    @Override
    public List<Object[]> getIncomesByMonth() {
        return getEntityManager()
                .createQuery("SELECT MONTH(e.date), SUM(e.amount) FROM " + entityClass.getSimpleName() + " e GROUP BY MONTH(e.date)")
                .getResultList();
    }

    @Override
    public List<Object[]> getExpensesByMonth() {
        return getEntityManager()
                .createQuery("SELECT MONTH(e.date), SUM(e.amount) FROM " + entityClass.getSimpleName() + " e GROUP BY MONTH(e.date)")
                .getResultList();
    }

    @Override
    public List<T> getBudgetsForCurrentMonth() {
        // Implement the logic to fetch budgets for the current month

        LocalDate currentDate = LocalDate.now();
        int currentMonth = currentDate.getMonthValue();

        return getEntityManager()
                .createQuery("SELECT b FROM " + entityClass.getSimpleName() + " b WHERE MONTH(b.date) = :currentMonth", entityClass)
                .setParameter("currentMonth", currentMonth)
                .getResultList();
    }

}
