import java.util.*;

public class Income {
    private String name;
    private double amount;
    private Date date;

    public Income(String n, double a) {
        name = n;
        amount = a;
        date = new Date();
    }
}
