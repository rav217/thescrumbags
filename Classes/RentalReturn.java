/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.util.*;
import java.math.BigDecimal;

/**
 *
 * @author benscandell
 */
public class RentalReturn extends Transaction {

    private Rental rental;
    private int daysLate;
    private int rentalID;

    public RentalReturn(int rentalID) {
        super();
        this.rentalID = rentalID;
        DBHandler dbh=DBHandler.getInstance();
    }

    public Rental getRental() {
        return rental;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public int getRentalID() {
        return rentalID;
    }

    public boolean checkIfLate() {
        GregorianCalendar now=this.date;
        if(now.after(rental.getReturnDate())) {
            daysLate=now.get(Calendar.DAY_OF_YEAR)-rental.getReturnDate().get(Calendar.DAY_OF_YEAR);
            BigDecimal bd=rental.getTotal().getAmount().multiply(new BigDecimal(daysLate/10));
            Money m=new Money(bd);
            setTotal(m);
            return true;
        }
        return false;
    }

    @Override
    public void accept(Payment p) {
        boolean b = p.verify(this);
        if (!b) {
            System.out.println("Payment was not accepted.");
        }
    }

    @Override
    public void updateInventory() {
        //use dbh, itemsReturned and lineItems to update inventory
    }

}
