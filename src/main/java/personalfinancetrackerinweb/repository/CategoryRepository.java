package personalfinancetrackerinweb.repository;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class CategoryRepository extends GenericAbstractRepository<Category> {

    @PersistenceContext(name = "pft")
    
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public CategoryRepository() {
        super(Category.class);
    }
    
    public List<Category> findByCategoryType(CategoryType type) {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c WHERE c.type = :type", Category.class);
        query.setParameter("type", type);
        return query.getResultList();
    }
   
    
    
}
