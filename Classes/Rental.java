/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

/**
 *
 * @author benscandell
 */
public class Rental extends Transaction {
    private int rentalPeriod;
    private boolean returned;
    
    private Rental() { super(); }
    public Rental(int rentalPeriod) {
        super();
        this.rentalPeriod=rentalPeriod;
    }
    
    public boolean isReturned() { return returned; } 
    
    public void completeReturn() { returned=true; }
    
    public int getRentalPeriod() { return rentalPeriod; }
    
    public void setRentalPeriod(int days) { rentalPeriod=days; }
}