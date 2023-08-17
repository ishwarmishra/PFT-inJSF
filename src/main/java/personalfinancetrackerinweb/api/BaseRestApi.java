package personalfinancetrackerinweb.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import personalfinancetrackerinweb.model.User;


public abstract class BaseRestApi {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Context
    private HttpServletRequest httpServletRequest;
    
    protected User getLoggedInUser() {
        return (User) httpServletRequest.getSession().getAttribute("loggedInUser");
    }
    
}
