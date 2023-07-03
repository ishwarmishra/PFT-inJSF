package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-03T17:16:33")
@StaticMetamodel(ExpenseEntity.class)
public class ExpenseEntity_ extends AbstractEntity_ {

    public static volatile SingularAttribute<ExpenseEntity, Date> date;
    public static volatile SingularAttribute<ExpenseEntity, BigDecimal> amount;
    public static volatile SingularAttribute<ExpenseEntity, String> name;
    public static volatile SingularAttribute<ExpenseEntity, String> category;

}