package personalfinancetrackerinweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.IncomeEntity;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-04T13:37:35")
@StaticMetamodel(CategoryEntity.class)
public class CategoryEntity_ extends AbstractEntity_ {

    public static volatile ListAttribute<CategoryEntity, IncomeEntity> incomes;
    public static volatile SingularAttribute<CategoryEntity, String> name;
    public static volatile SingularAttribute<CategoryEntity, CategoryType> type;
    public static volatile SingularAttribute<CategoryEntity, Integer> categoryId;

}