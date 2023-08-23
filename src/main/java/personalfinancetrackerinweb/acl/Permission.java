package personalfinancetrackerinweb.acl;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import personalfinancetrackerinweb.model.AbstractEntity;

@Entity
@Table(name = "permission_entity")
public class Permission extends AbstractEntity implements Serializable{
    
    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
    
    @Enumerated(EnumType.STRING)
    private ActionType actionType;
    
    private boolean allowAction;

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public boolean isAllowAction() {
        return allowAction;
    }

    public void setAllowAction(boolean allowAction) {
        this.allowAction = allowAction;
    }
    
}