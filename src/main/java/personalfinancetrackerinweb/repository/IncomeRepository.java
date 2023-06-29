package personalfinancetrackerinweb.repository;

import javax.ejb.Stateless;

import personalfinancetrackerinweb.model.IncomeEntity;
import personalfinancetrackerinweb.repository.generic.GenericAbstractRepository;

@Stateless
public class IncomeRepository extends  GenericAbstractRepository<IncomeEntity> {

    public IncomeRepository() {
        super(IncomeEntity.class);
    }
}
