package personalfinancetrackerinweb.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named
@SessionScoped
public class DashboardBean implements Serializable {

    public String logout() {
        return "/login?faces-redirect=true";
    }
}
