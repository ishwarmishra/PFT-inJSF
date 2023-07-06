package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.Category;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-06T11:03:38")
@StaticMetamodel(Income.class)
public class Income_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Income, Date> date;
    public static volatile SingularAttribute<Income, BigDecimal> amount;
    public static volatile SingularAttribute<Income, String> name;
    public static volatile SingularAttribute<Income, Category> category;

}