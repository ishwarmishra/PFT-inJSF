package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.CategoryEntity;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-04T10:45:18")
@StaticMetamodel(ExpenseEntity.class)
public class ExpenseEntity_ extends AbstractEntity_ {

    public static volatile SingularAttribute<ExpenseEntity, Date> date;
    public static volatile SingularAttribute<ExpenseEntity, BigDecimal> amount;
    public static volatile SingularAttribute<ExpenseEntity, String> name;
    public static volatile SingularAttribute<ExpenseEntity, CategoryEntity> category;

}