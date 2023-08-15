package personalfinancetrackerinweb.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity implements GenericEntityInterface, Serializable {

    private String name;
        
    @Enumerated(EnumType.STRING)
    private CategoryType categoryType;
    
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @NotNull
    private User user;
     
    public Category() {
    }

    public Category(String name, CategoryType type) {
        this.name = name;
        this.categoryType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(CategoryType categoryType) {
        this.categoryType = categoryType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category otherCategory = (Category) obj;
        return Objects.equals(this.getId(), otherCategory.getId());
    }
    
}
