package personalfinancetrackerinweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class IncomeEntity extends AbstractEntity implements Serializable {
    private int id;
    private String name;
    private String category;
    private BigDecimal amount;
    private Date date;

    public IncomeEntity(int id, String name,String category, BigDecimal amount, Date date) {
        this.id = id;
        this.name = name;
        this.category=category;
        this.amount = amount;
        this.date = date;
    }
    public int getId() {
        return id;
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
