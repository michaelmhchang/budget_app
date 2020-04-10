import java.util.*;

public class Expense {
    private String name;
    private String type; // Savings or Spending
    private double amount;
    private Date date;

    public Expense(String n, String t, double a) {
        name = n;
        type = t;
        amount = a;
        date = new Date();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }
}
