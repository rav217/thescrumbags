package thescrumbags.Classes;

/*
 * Ben Candell
 * bsc218@lehigh.edu
 * CSE 216
 * Register class
 */

import java.util.Date;

/*
 * A class representing a cash register for the point-of-sale system
 */
public class Register {

    protected ProductCatalog catalog;
    protected Transaction currentTransaction;
    protected Date date;
    protected Store location;
    protected boolean isOpen;

    /**
     * Default constructor
     */
    public Register() {}

    /**
     * Constructor, assigns field values for Register object If no Sale
     * parameter passed, currentSale is set to null
     *
     * @param catalog the Store's product catalog
     */
    public Register(ProductCatalog catalog) {
        this.catalog = catalog;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public ProductCatalog getCatalog() {
        return catalog;
    }

    public Store getLocation() {
        return location;
    }

    /**
     * Creates a new Sale object and stores it in currentSale
     */
    public void makeNewTransaction() {
        currentTransaction = new Transaction();
    }
    

    public void endTransaction() {
        currentTransaction.becomeComplete();
    }

    public void enterItem(int id, int quantity) {
        ProductDescription desc = catalog.getProductDescription(id);
        currentTransaction.makeLineItem(desc, quantity);
    }

    public void makePayment(Money cashTendered) {
        currentTransaction.makePayment(cashTendered,false,"");
    }

    public void clearCurrentTransaction() {
        endTransaction();
    }

    public void openRegister() {
        isOpen = true;
    }

    public void closeRegister() {
        isOpen = false;
    }
}
