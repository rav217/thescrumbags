/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.math.BigDecimal;


/**
 *
 * @author benscandell
 */
public class SaleReturn extends Transaction {
   
    private final Transaction sale;
    private Reimbursement r;
    private final String reason;
    private final int saleID;
    private int itemsReturned=0;
    
    /**
     * saleID is going to be the date with all symbols removed
     * @param saleID the ID number printed on the original receipt
     * @param reason
     */
    //made serious changes: check w ben
    public SaleReturn(int saleID, String reason) {
        super();
        this.saleID=saleID;
        this.reason=reason;
        
        //add neccessary dbhandler code
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.sale = db.findTransaction("S", saleID);
        db.closeConnection();
        if (this.sale == null) System.out.println("Sale not found in database");
    }
    
    public Transaction getSale() { return sale; }
    
    public String getReason() { return reason; }
    
    public int getSaleID() { return saleID; } 
    
    public Money negateTotal() {
        return getTotal().multiply(new BigDecimal(-1));
    }
    
    @Override
    public void becomeComplete() {
        for(LineItem l: lineItems) {
            itemsReturned+=1;
        } 
        super.becomeComplete();     
    }
    
    @Override
    public void updateInventory() {
        //use dbh, itemsReturned and lineItems to update inventory
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("S", lineItems);
        db.updateInventory("saleproducts", lineItems, true);
        db.closeConnection();
    }
    
    @Override
    public boolean accept(Reimbursement r) {
        return r.verify(this);
    }
}
