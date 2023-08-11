package personalfinancetrackerinweb.controller;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.inject.Inject;


public class LoginCheckListener implements PhaseListener {
    
    @Inject
    UserBean userBean;
    
    private Boolean isUserLoggedIn() {
        return null != userBean.getUser();
    }

    @Override
    public void afterPhase(PhaseEvent event) {
         FacesContext facesContext = event.getFacesContext();     
         String currentPage = facesContext.getViewRoot().getViewId();      
         boolean isLoginPage = (currentPage.lastIndexOf("login.xhtml") > -1);      
         if (!isLoginPage && !isUserLoggedIn()) {           
             NavigationHandler nh = facesContext.getApplication().getNavigationHandler();          
             nh.handleNavigation(facesContext, null, "/login.xhtml?faces-redirect=true");
        }
    }
    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}