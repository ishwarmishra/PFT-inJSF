package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class CategoryRepositoryImpl extends GenericAbstractRepository<Category> {

    @PersistenceContext(name = "pft")

    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public CategoryRepositoryImpl() {
        super(Category.class);
    }

}
