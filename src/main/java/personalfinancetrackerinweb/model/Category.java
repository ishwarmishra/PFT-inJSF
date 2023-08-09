package personalfinancetrackerinweb.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category extends AbstractEntity implements GenericEntityInterface, Serializable {

    private String name;
    
    @Enumerated(EnumType.STRING)
    private CategoryType type;
    
    public Category() {
    }

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
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
