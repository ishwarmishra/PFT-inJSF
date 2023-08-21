package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.Category;
import personalfinancetrackerinweb.model.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-21T15:20:34")
@StaticMetamodel(Budget.class)
public class Budget_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Budget, Date> fromDate;
    public static volatile SingularAttribute<Budget, BigDecimal> amount;
    public static volatile SingularAttribute<Budget, Date> toDate;
    public static volatile SingularAttribute<Budget, Category> category;
    public static volatile SingularAttribute<Budget, User> user;

}