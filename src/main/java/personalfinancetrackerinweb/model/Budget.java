package personalfinancetrackerinweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "budget")
public class Budget extends AbstractEntity implements Serializable, GenericEntityInterface {

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @NotNull
    private User user;
   
    private BigDecimal amount;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fromDate;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private  Date toDate;
    
    
    public Budget() {
    }

    public Budget(Category category, BigDecimal amount,Date fromDate,Date toDate) {
        this.category = category;
        this.amount = amount;
        this.fromDate=fromDate;
        this.toDate=toDate;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }
    
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    } 

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
