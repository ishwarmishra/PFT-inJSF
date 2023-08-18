package personalfinancetrackerinweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-18T17:11:55")
@StaticMetamodel(User.class)
public class User_ extends AbstractEntity_ {

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, String> roles;
    public static volatile SingularAttribute<User, String> username;

}