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
@Table(name = "income_entity")
public class Income extends AbstractEntity implements Serializable{

   private String name;
   private BigDecimal amount;
   

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;
    
    
    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private Category category;

    
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @NotNull
    private User user;
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
   
}
