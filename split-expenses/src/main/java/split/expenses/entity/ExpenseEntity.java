package split.expenses.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "expenses", schema = "public")
public class ExpenseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private double total;

    private double totalByUser;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    private Date date;

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalByUser() {
        return totalByUser;
    }

    public void setTotalByUser(double totalByUser) {
        this.totalByUser = totalByUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
