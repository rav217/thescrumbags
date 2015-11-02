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
    private Reimbursement r;
    private String reason;
    private int saleID;
    private int itemsReturned=0;
    
    /**
     * saleID is going to be the date with all symbols removed
     * @param saleID the ID number printed on the original receipt
     */
    public SaleReturn(int saleID, String reason) {
        super();
        this.saleID=saleID;
        this.reason=reason;
        DBHandler dbh=DBHandler.getInstance();
        //use dbh to find corresponding Sale object
        sale=new Sale();
    }
    
    public Sale getSale() { return sale; }
    
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
    }
    
    @Override
    public void accept(Reimbursement r) {
        boolean b=r.verify(this);
        if (!b) {
            System.out.println("Error reimbursing customer...");
        }
    }
}
