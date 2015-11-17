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
        this.daysLate = this.getDaysLate();
        this.setLateFee();
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

      //creates a new LineItem for the sale given a description and quantity,
    //adds new LineItem to the lineItems ArrayList, total updated with new subtotal
    @Override
    public LineItem makeLineItem(ProductDescription desc, int qty) {
        LineItem lineItem = new LineItem(desc, qty);
        lineItems.add(lineItem);
        // total = total.add(lineItem.getSubtotal());
        return lineItem;
    }
    
    @Override
    public LineItem makeLineItem(LineItem li) {
        LineItem lineItem = new LineItem(li);
        lineItems.add(lineItem);
        // total = total.add(lineItem.getSubtotal());
        return lineItem;
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
    public final int getDaysLate() {
        GregorianCalendar now = this.date;
        if (now.after(rental.getReturnDate())) { //TODO: either fix this or change DBHandler
            daysLate = now.get(Calendar.DAY_OF_YEAR) - rental.getReturnDate().get(Calendar.DAY_OF_YEAR);
            
            return daysLate;
        }
        else
            return 0;
    }
    
    public final void setLateFee()
    {
        double totalFee = 0;
        double fee = 0;
        
        System.out.println("setting late fee");
        for (LineItem lineItem : rental.getLineItems()) {
            System.out.println(lineItem.toString());
            fee = lineItem.getSubtotal().getAmount().doubleValue() * .25;
            totalFee += fee;            
        }
        
        totalFee *= this.daysLate;
        this.total = new Money(totalFee);        
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
        db.addTransaction("RR", this.lineItems, "", this.rentalID, this.date); //added date
        db.updateInventory("rentalproducts", lineItems, true);
        db.closeConnection();
    }
    
    public Receipt makeNewReceipt()
    {
        return null;
    }
    

}
