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
    protected Sale currentSale;
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

    public Sale getCurrentSale() {
        return currentSale;
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
    public void makeNewSale() {
        currentSale = new Sale();
    }

    public void endSale() {
        currentSale.becomeCompleted();
    }

    public void enterItem(ItemID id, int quantity) {
        ProductDescription desc = catalog.getProductDescription(id);
        currentSale.makeLineItem(desc, quantity);
    }

    public void makePayment(Money cashTendered) {
        Payment p = new Payment(cashTendered, false); //creates new cash payment
        currentSale.makePayment(p);
    }

    public void clearCurrentSale() {
        endSale();
    }

    public void openRegister() {
        isOpen = true;
    }

    public void closeRegister() {
        isOpen = false;
    }
}
