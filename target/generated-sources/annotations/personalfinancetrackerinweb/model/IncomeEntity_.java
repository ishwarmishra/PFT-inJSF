package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.CategoryEntity;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-04T17:46:54")
@StaticMetamodel(IncomeEntity.class)
public class IncomeEntity_ extends AbstractEntity_ {

    public static volatile SingularAttribute<IncomeEntity, Date> date;
    public static volatile SingularAttribute<IncomeEntity, BigDecimal> amount;
    public static volatile SingularAttribute<IncomeEntity, CategoryEntity> categoryEntity;
    public static volatile SingularAttribute<IncomeEntity, String> name;

}