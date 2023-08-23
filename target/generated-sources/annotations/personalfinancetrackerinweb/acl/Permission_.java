package personalfinancetrackerinweb.acl;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import personalfinancetrackerinweb.acl.ActionType;
import personalfinancetrackerinweb.acl.ResourceType;
import personalfinancetrackerinweb.acl.UserRole;
import personalfinancetrackerinweb.model.AbstractEntity_;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-23T17:01:27")
@StaticMetamodel(Permission.class)
public class Permission_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Permission, ActionType> actionType;
    public static volatile SingularAttribute<Permission, Boolean> allowAction;
    public static volatile SingularAttribute<Permission, UserRole> userRole;
    public static volatile SingularAttribute<Permission, ResourceType> resourceType;

}