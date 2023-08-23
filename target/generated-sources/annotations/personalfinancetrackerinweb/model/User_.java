package personalfinancetrackerinweb.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.acl.UserRole;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-23T17:01:27")
@StaticMetamodel(User.class)
public class User_ extends AbstractEntity_ {

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, UserRole> userRole;
    public static volatile SingularAttribute<User, String> username;

}