package personalfinancetrackerinweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.model.CategoryType;
import personalfinancetrackerinweb.model.User;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-23T17:01:27")
@StaticMetamodel(Category.class)
public class Category_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Category, CategoryType> categoryType;
    public static volatile SingularAttribute<Category, String> name;
    public static volatile SingularAttribute<Category, User> user;

}