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

    private Transaction rental;
    private int daysLate;
    private int rentalID;

    //check with ben
    public RentalReturn(int rentalID) throws ClassCastException {
        super();
        this.rentalID = rentalID;
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.rental = db.findTransaction("R", rentalID);
        try {
            if (rental instanceof Rental) {
                rental = (Rental) rental;
            } else {
                throw new ClassCastException();
            }
        } catch (ClassCastException ex) {
            System.out.println("Error casting to Rental object");
        }
        db.closeConnection();
        if (this.rental == null) {
            System.out.println("Rental not found in database");
        }
    }

    public Transaction getRental() {
        return rental;
    }

    public int getDaysLate() {
        return daysLate;
    }

    public int getRentalID() {
        return rentalID;
    }

    public boolean checkIfLate() {
        GregorianCalendar now = this.date;
        if (now.after(rental.getReturnDate())) { //TODO: either fix this or change DBHandler
            daysLate = now.get(Calendar.DAY_OF_YEAR) - rental.getReturnDate().get(Calendar.DAY_OF_YEAR);
            BigDecimal bd = rental.getTotal().getAmount().multiply(new BigDecimal(daysLate / 10));
            Money m = new Money(bd);
            setTotal(m);
            return true;
        }
        return false;
    }

    @Override
    public boolean accept(Payment p) {
        boolean b = p.verify(this);
        if (!b) {
            System.out.println("Payment was not accepted.");
        }
        return true;
    }

    @Override
    public void updateInventory() {
        //use dbh, itemsReturned and lineItems to update inventory
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("R", lineItems);
        db.updateInventory("rentalproducts", lineItems, true);
        db.closeConnection();
    }

}
