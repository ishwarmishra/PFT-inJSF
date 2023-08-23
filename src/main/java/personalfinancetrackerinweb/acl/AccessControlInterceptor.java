package personalfinancetrackerinweb.acl;

import java.io.Serializable;
import java.lang.reflect.Method;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import personalfinancetrackerinweb.model.User;
import personalfinancetrackerinweb.repository.AccessControlRepository;

@RequiredPermission
@Interceptor
@Dependent
@Priority(Interceptor.Priority.APPLICATION)
public class AccessControlInterceptor implements Serializable {

    @Inject
    private AccessControlRepository accessControlRepository;

    @AroundInvoke
    public Object checkAccess(InvocationContext context) throws Exception {

        boolean isAllowed = false;
        Method m = context.getMethod();

        RequiredPermission methodName = m.getAnnotation(RequiredPermission.class);

        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
        .getExternalContext().getRequest();
        User user = (User) httpServletRequest.getSession().getAttribute("loggedInClient");
        UserRole role = user.getUserRole();
       
        if (methodName != null) {
            ActionType retrievAction = methodName.action();
            ResourceType retrieveResource = methodName.resource();
            System.out.println("");
           isAllowed = accessControlRepository.permissionAllowed(role, retrieveResource, retrievAction);
            System.out.println("");
        }
        if (isAllowed) {
            return context.proceed();
        } else {
            throw new SecurityException("Access Denied");
        }

    }

}
