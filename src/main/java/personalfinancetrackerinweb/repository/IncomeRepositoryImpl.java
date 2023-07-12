package personalfinancetrackerinweb.repository;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.Income;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class IncomeRepositoryImpl extends GenericAbstractRepository<Income> {

    @PersistenceContext(name = "pft")
    private EntityManager entityManager;

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    public IncomeRepositoryImpl() {
        super(Income.class);
    }

}
