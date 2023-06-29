package personalfinancetrackerinweb.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-29T12:52:53")
@StaticMetamodel(IncomeEntity.class)
public class IncomeEntity_ { 

    public static volatile SingularAttribute<IncomeEntity, Date> date;
    public static volatile SingularAttribute<IncomeEntity, BigDecimal> amount;
    public static volatile SingularAttribute<IncomeEntity, String> name;
    public static volatile SingularAttribute<IncomeEntity, Integer> id;
    public static volatile SingularAttribute<IncomeEntity, String> category;

}