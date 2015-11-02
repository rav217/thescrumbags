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

    private ProductCatalog catalog;
    private Transaction currentTransaction;
    private boolean isOpen;
    private DBHandler dbHandler;
    private UserManager userManager;

    /**
     * Default constructor
     */
    public Register() {
        //TODO: we need to pull the product catalog from the database
    }

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
    
    /**
     * Creates a new Sale object and stores it in currentSale
     */
    public void makeNewSale() {
        this.currentTransaction = new Sale();
    }
    
    public void makeNewRental(Date returnDate) {
        this.currentTransaction = new Rental(returnDate);
    }
    
    //public void makeNewReturn() {
    //    this.currentTransaction = new Return();
    //}

    public void endTransaction() {
        currentTransaction.becomeComplete();
    }

    public LineItem enterItem(int id, int quantity) {
        ProductDescription desc = catalog.getProductDescription(id);
        return currentTransaction.makeLineItem(desc, quantity);
    }

    public void makeCashPayment(Money cashGiven) {
        Payment p = new CashPayment(cashGiven);
        this.currentTransaction.accept(p);
    }
    
    public void makeCreditPayment(String cardNum) {
        Payment p = new CreditPayment(cardNum);
        this.currentTransaction.accept(p);
    }

    public void cancelTransaction() {
        endTransaction();
    }
}
