package thescrumbags.Classes;

/**
 * Scrumbags POS
 * CSE 216
 * @author Ben Candell
 * Money class
 */

public class Money {
    private double amount;
    
    public Money() {}
    
    public Money(double d) { 
        this.amount=d;
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
        if (amount==m.getAmount()) return true;
        return false;
    }
    
    public int compareTo(Money m) { 
        if (amount > m.getAmount()) return 10;
        else if(amount < m.getAmount()) return -10;
        else return 0;
    }
}