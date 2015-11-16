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

    /**
     * Constructor for RentalReturn
     * Grabs corresponding rental from DB
     * @param rentalID the rental ID number corresponding to the return
     * @throws ClassCastException if transaction is not a Rental
     */
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

    /**
     * Get method for rental corresponding to rental return
     * @return the corresponding rental
     */
    public Transaction getRental() {
        return rental;
    }

    /**
     * Get method for number of days late
     * @return number of days late
     *
    public int getDaysLate() {
        return daysLate;
    }
*/
    /**
     * Get method for rental ID number
     * @return rental ID
     */
    public int getRentalID() {
        return rentalID;
    }

    /**
     * Compares today's date to original rental's date.
     * If late, creates a new Money object and sets it as the total, returns true.
     * Otherwise returns false
     * @return whether or not return is late
     */
    public int getDaysLate() {
        GregorianCalendar now = this.date;
        if (now.after(rental.getReturnDate())) { //TODO: either fix this or change DBHandler
            daysLate = now.get(Calendar.DAY_OF_YEAR) - rental.getReturnDate().get(Calendar.DAY_OF_YEAR);
            
            return daysLate;
        }
        else
            return 0;
    }
    
    public double getLateFee()
    {
        int daysLate = getDaysLate();
        double total = 0;
        double fee = 0;
        
        for(int i=0; i < rental.lineItems.size(); i++)
        {
            fee = rental.lineItems.get(i).getSubtotal().getAmount().doubleValue() * .25;
            total += fee;            
        }
        
        total *= daysLate;
        return total;        
    }

    /**
     * Accepts a payment to be processed if the rental is returned late.
     * @param p the payment to be accepted
     * @return whether or not the payment went through
     */
    @Override
    public boolean accept(Payment p) {
        boolean b = p.verify(this);
        if (!b) {
            System.out.println("Payment was not accepted.");
        }
        return true;
    }

    /**
     * Updates inventory in DB after transaction is complete.
     */
    @Override
    public void updateInventory() {
        //use dbh, itemsReturned and lineItems to update inv
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("RR", this.lineItems, "", this.rentalID);
        db.updateInventory("rentalproducts", lineItems, true);
        db.closeConnection();
    }
    
    public Receipt makeNewReceipt()
    {
        return null;
    }
    

}
