package personalfinancetrackerinweb.acl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;


@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequiredPermission {   
    @Nonbinding
    ActionType action() default ActionType.READ;  
    
    @Nonbinding
    ResourceType resource() default ResourceType.USER;

}