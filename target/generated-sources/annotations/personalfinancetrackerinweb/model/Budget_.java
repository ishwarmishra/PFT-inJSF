package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.Category;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-21T17:41:00")
@StaticMetamodel(Budget.class)
public class Budget_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Budget, Date> date;
    public static volatile SingularAttribute<Budget, BigDecimal> amount;
    public static volatile SingularAttribute<Budget, Category> category;

}