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
public class RentalReturn extends Transaction {

    private Rental rental;
    private int daysLate;
    private int rentalID;

    public RentalReturn(int rentalID) {
        super();
        this.rentalID = rentalID;
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

    public void checkIfLate() {
        GregorianCalendar now=this.date;
        if(now.after(rental.getReturnDate())) {
            daysLate=now.get(Calendar.DAY_OF_YEAR)-rental.getReturnDate().get(Calendar.DAY_OF_YEAR);
            Money m=new Money(daysLate*.1*rental.getTotal().getAmount());
            setTotal(m);
        }
    }

    public void accept(Payment p) {
        boolean b = p.verify(this);
        if (!b) {
            System.out.println("Payment was not accepted.");
        }
    }

    public void updateInventory() {
    }

}
