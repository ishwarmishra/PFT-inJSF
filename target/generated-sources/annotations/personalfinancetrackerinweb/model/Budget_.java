package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.Category;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-11T15:42:43")
@StaticMetamodel(Budget.class)
public class Budget_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Budget, BigDecimal> amount;
    public static volatile SingularAttribute<Budget, Category> category;

}