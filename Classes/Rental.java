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
    private int rentalPeriod;
    
    private Rental() { super(); }
    public Rental(int rentalPeriod) {
        super();
        this.rentalPeriod=rentalPeriod;
    }
    
    public int getRentalPeriod() { return rentalPeriod; }
    
    public void setRentalPeriod(int days) { rentalPeriod=days; }
}