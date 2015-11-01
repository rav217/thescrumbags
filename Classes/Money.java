package thescrumbags.Classes;

/**
 * Scrumbags POS
 * CSE 216
 * @author Ben Candell
 * Money class
 */

<<<<<<< HEAD
import java.util.*;

=======
>>>>>>> 0c14f0f34f8787ba50c4f4aad45b7561fcd05204
public class Money {
    private static double amount;
    
    public Money() {}
    
    public Money(double d) { 
        setAmount(d);
    }
    public Money(Money m) {
        copy(m); 
    }
    
    public static void copy(Money m) {
        amount=m.getAmount();
    }
    
    public static void setAmount(double d) { 
        amount=d;
    }
    
    public double getAmount() { return amount; } 
    
    public Money add(Money m) {
        Money toReturn=new Money(amount+m.getAmount());
        return toReturn;
    }
    
    public Money add(double d){
        Money toReturn=new Money(amount+d);
        return toReturn;
    }
    
    public Money subtract(Money m) {
        Money toReturn=new Money(amount-m.getAmount());
        return toReturn;
    }
    
    public Money subtract(double d)
    {
        Money toReturn=new Money(amount-d);
        return toReturn;
    }
    
    public Money times(double d)
    {
        Money toReturn=new Money(amount*d);
        return toReturn;
    }
    
    public boolean equals(Money m) {
<<<<<<< HEAD
        return this.amount==m.getAmount();
=======
        if (amount==m.getAmount()) return true;
        return false;
>>>>>>> 0c14f0f34f8787ba50c4f4aad45b7561fcd05204
    }
    
    public int compareTo(Money m) { 
        if (amount > m.getAmount()) return 10;
        else if(amount < m.getAmount()) return -10;
        else return 0;
    }
}
