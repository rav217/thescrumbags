package thescrumbags.Classes;

/**
 * Scrumbags POS CSE 216
 *
 * @author Ben Candell 
 * Money class
 */
import java.math.BigDecimal;
import java.util.*;

public class Money implements Comparable {

    private BigDecimal amount;

    /**
     * Creates a Money object and initializes amount to 0.
     */
    public Money() {
        BigDecimal zero = new BigDecimal(0);
        this.amount = zero;

    }
    /**
     * Creates a Money object with amount initialized to the BigDecimal passed
     * as a parameter.
     * @param amount BigDouble that defines the amount of the Money object.
     */
    public Money(BigDecimal a) {
        this.amount = a;
    }

    /**
     * Creates a Money object with amount initialized to the BigDecimal passed
     * as a parameter.
     * @param amount double that defines the amount of the Money object.
     */
    public Money (double a) {
        this.amount = new BigDecimal(a);
    }
    
    /**
     * Returns the value of the amount of the calling Money object
     * @return BigDecimal amount of the calling object
     */
    public BigDecimal getAmount() {
        return new BigDecimal(this.amount.doubleValue());
    }

    /**
     * 
     * @param m
     * @return 
     */
    public Money add(Money m) {
        System.out.println("money in: " +m);
        BigDecimal temp = this.amount;
        System.out.println("before add: " + temp);
        temp = temp.add(m.getAmount());
        System.out.println("after add: " +temp);
        return new Money(temp);
    }

    public Money add(BigDecimal bd) {
        return new Money(this.amount.add(bd));
    }

    public Money subtract(Money m) {
        // this is much safer
        BigDecimal temp = this.amount;
        temp = temp.subtract(m.getAmount());
        return new Money(temp);
    }

    public Money subtract(BigDecimal bd) {
        return new Money(this.amount.subtract(bd));
    }

    public Money multiply(BigDecimal bd) {
        return new Money(this.amount.multiply(bd));

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Money) {
            Money money = (Money) o;
            return amount.equals(money.getAmount());
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.amount);
        return hash;
    }

    @Override
    public int compareTo(Object o) throws ClassCastException  {
        if (o instanceof Money) {
            Money money = (Money) o;
            if (this.amount.doubleValue() < money.getAmount().doubleValue()) {
                return -1;
            }
            else if (this.amount.doubleValue() == money.getAmount().doubleValue()) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else throw new ClassCastException();
    }
    
    @Override
    public String toString() {
        float d=amount.floatValue();
        String str=String.format("%-5.2f", d);
        return str;
    }
}
