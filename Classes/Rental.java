/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.util.*;
/**
 *
 * @author benscandell
 */
public class Rental extends Transaction {
    
    private GregorianCalendar returnDate;
    private boolean returned;
    private int rentalPeriod;
    
    private Rental() { super(); }
    
    public Rental(GregorianCalendar returnDate) {
        super();
        this.returnDate=returnDate;
    }
    
    public boolean isReturned() { return returned; } 
    
    public void completeReturn() { returned=true; }
    
    public void setReturnDate(int numDays) { 
        this.rentalPeriod = numDays;
        GregorianCalendar returnItemsOn = new GregorianCalendar();
        returnItemsOn.add(Calendar.DAY_OF_YEAR, numDays);
        this.returnDate = returnItemsOn;
    }
    
    public Calendar getReturnDate()  {
        return returnDate; 
    }
    
    @Override
    public void accept(Payment p) {
        boolean b=p.verify(this);
        if(!b) {
            System.out.println("Payment was not accepted.");
        }
    }
    
    @Override
    public void updateInventory() {
        
    }
}