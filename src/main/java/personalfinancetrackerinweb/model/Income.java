package personalfinancetrackerinweb.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "income_entity")
public class Income extends AbstractEntity implements Serializable, GenericEntityInterface {

    private String name;

    private BigDecimal amount;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    
    private Category category;


    public Income() {
    }

    public String getNameIncome() {
        return name;
    }

    public void setNameIncome(String nameIncome) {
        this.name = nameIncome;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Income(String name,Category category ,BigDecimal amount, Date date) {
        this.name = name;
        this.category=category;
        this.amount = amount;
        this.date = date;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
