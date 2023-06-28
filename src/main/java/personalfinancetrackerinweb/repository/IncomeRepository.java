package personalfinancetrackerinweb.repository;

import javax.persistence.PersistenceContext;
import personalfinancetrackerinweb.model.IncomeEntity;

public class IncomeRepository<T> extends AbstractRepository<IncomeEntity> {
    
    @PersistenceContext(name="pft")

     
}
