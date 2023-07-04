package personalfinancetrackerinweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.CategoryType;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-07-04T10:45:18")
@StaticMetamodel(CategoryEntity.class)
public class CategoryEntity_ { 

    public static volatile SingularAttribute<CategoryEntity, String> name;
    public static volatile SingularAttribute<CategoryEntity, CategoryType> type;
    public static volatile SingularAttribute<CategoryEntity, Integer> categoryId;

}