/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.util.Date;
/**
 *
 * @author benscandell
 */
public class Rental extends Transaction {
    
    private Date returnDate;
    private boolean returned;
    
    private Rental() { super(); }
    
    public Rental(Date returnDate) {
        super();
        this.returnDate=returnDate;
    }
    
    public boolean isReturned() { return returned; } 
    
    public void completeReturn() { returned=true; }
    
    public Date getRentalPeriod() { return this.returnDate; }
    
    public void setRentalPeriod(Date date) { this.returnDate=date; }
}