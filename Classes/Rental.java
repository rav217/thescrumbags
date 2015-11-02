/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.util.Date;
import java.util.Calendar;
/**
 *
 * @author benscandell
 */
public class Rental extends Transaction {
    
    private Calendar returnDate;
    private boolean returned;
    
    private Rental() { super(); }
    
    public Rental(Calendar returnDate) {
        super();
        this.returnDate=returnDate;
    }
    
    public boolean isReturned() { return returned; } 
    
    public void completeReturn() { returned=true; }
    
    public Calendar setReturnDate() { return this.returnDate; }
    
    public void setReturnDate(Calendar date) { this.returnDate=date; }
    
    public Calendar getReturnDate()  {
        return returnDate; 
    }
}