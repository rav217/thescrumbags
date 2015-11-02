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
    private Sale sale;
    private String reason;
    private int saleID;
    private int itemsReturned;
    
    /**
     * saleID is going to be the date with all symbols removed
     * @param saleID the ID number printed on the original receipt
     */
    public SaleReturn(int saleID) {
        super();
        this.saleID=saleID;
        DBHandler dbh=DBHandler.getInstance();
        //use dbh to find corresponding Sale object
        sale=new Sale();
    }
    
    public Sale getSale() { return sale; }
    
    public String getReason() { return reason; }
    
    public int getSaleID() { return saleID; } 
    
    public void accept(Reimbursement r) {
        System.out.println("Give the customer $"+r.getAmount().multiply(BigDecimal.ZERO.subtract(BigDecimal.ONE)));
        
    }
    
    @Override
    public void becomeComplete() {
        itemsReturned=0;
        for(LineItem l: lineItems) {
            itemsReturned+=1;
        }
        super.becomeComplete();
        
    }
    
    @Override
    public void updateInventory() {
        //use dbh, itemsReturned and lineItems to update inventory
    }
}
