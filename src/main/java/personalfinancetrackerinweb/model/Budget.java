package personalfinancetrackerinweb.model;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "budget")
public class Budget extends AbstractEntity implements Serializable, GenericEntityInterface {

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    private BigDecimal amount;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    public Budget() {
    }

    public Budget(Category category, BigDecimal amount,Date date) {
        this.category = category;
        this.amount = amount;
       this.date=date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
