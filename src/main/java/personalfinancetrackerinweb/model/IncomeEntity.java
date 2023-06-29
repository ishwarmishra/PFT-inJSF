package personalfinancetrackerinweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "income_entity")
public class IncomeEntity extends AbstractEntity implements Serializable, GenericEntityInterface {

    private String name;
    private String category;
    private BigDecimal amount;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    public IncomeEntity(String name, String category, BigDecimal amount, Date date) {
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public IncomeEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
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
