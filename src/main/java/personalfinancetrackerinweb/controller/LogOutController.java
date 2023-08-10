package personalfinancetrackerinweb.controller;

import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named
@SessionScoped
public class LogOutController implements Serializable {

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.invalidateSession();

        try {
            externalContext.redirect("login.xhtml");
        } catch (IOException e) {
            FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Logout Failed", "An error occurred during logout.");
            FacesContext.getCurrentInstance().addMessage(null, errorMessage);
            e.printStackTrace();
        }

        return null; 
    }
}
