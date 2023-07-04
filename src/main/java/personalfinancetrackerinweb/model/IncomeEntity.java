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
@Table(name = "income_entity")
public class IncomeEntity extends AbstractEntity implements Serializable, GenericEntityInterface {

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    private BigDecimal amount;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    // Constructors, getters, and setters

    public IncomeEntity() {
    }

    public IncomeEntity(String name, CategoryEntity category, BigDecimal amount, Date date) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
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
