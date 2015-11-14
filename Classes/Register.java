package thescrumbags.Classes;

/**
 * A Singleton class representing a cash register for a POS system
 * @author The Scrumbags
 */
public class Register {

    private ProductCatalog catalog;
    private Transaction currentTransaction;
    private Payment currentPayment;
    private UserManager userManager;
    private static Register uniqueInst;

    /**
     * Default constructor: pulls product catalog and employee records from db
     */
    private Register() { } //default constructor for now
    
    /**
     * Applies Singleton pattern to Register
     * @return the static synchronized instance of Register
     */
    public static synchronized Register getInstance(){
        if (uniqueInst == null){
            uniqueInst = new Register();
        }
        return uniqueInst;
    }

    /**
     * Get method for current transaction
     * @return the current transaction
     */
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    /**
     * Get method for current payment
     * @return the current payment
     */
    public Payment getCurrentPayment() {
        return this.currentPayment;
    }
    
    /**
     * Set method for current payment
     * @param p the new payment
     */
    public void setCurrentPayment(Payment p) {
        this.currentPayment = p;
    }
            
    /**
     * Get method for the register's product catalog
     * @return the product catalog
     */
    public ProductCatalog getCatalog() {
        return catalog;
    }

    /**
     * Creates a new Sale object and stores it as the current transaction
     * Grabs updated product catalog each time
     */
    public void makeNewSale() {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.catalog = db.initSPC();
        db.closeConnection();
        this.currentTransaction = new Sale();
    }

    /**
     * Creates a new Rental object and stores it as the current transaction
     * Grabs updated product catalog
     * @param numDays the rental period in days
     */
    public void makeNewRental(int numDays) {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.catalog = db.initRPC();
        db.closeConnection();
        this.currentTransaction = new Rental(numDays);
    }

    /**
     * Ends the current transaction upon completion
     */
    public void endTransaction() {
        currentTransaction.updateInventory();
        currentTransaction.becomeComplete();
    }

    /**
     * Creates a new line item for the current transaction
     * @param id product's item id number
     * @param quantity quantity of product
     * @return the new line item
     */
    public LineItem enterItem(int id, int quantity) {
        ProductDescription desc = catalog.getProductDescription(id);
        return currentTransaction.makeLineItem(desc, quantity);
    }
    
    /**
     * Creates a new line item for the current transaction
     * @param li the line item to be added
     * @return the new line item
     */
    public LineItem enterItem(LineItem li) {
        return currentTransaction.makeLineItem(li.getProductDescription(), li.getQuantity());
    }

    /**
     * Creates a new cash payment object 
     * Current transaction accepts payment 
     * @param cashGiven cash tendered
     */
    public void makeCashPayment(Money cashGiven) {
        Payment p = new CashPayment(cashGiven);
        this.currentTransaction.accept(p);
    }

    /**
     * Creates a new credit payment object
     * Current transaction accepts payment
     * @param cardNum credit card number 
     */
    public void makeCreditPayment(String cardNum) {
        Payment p = new CreditPayment(cardNum);
        this.currentTransaction.accept(p);
    }

    /**
     * Creates a new sale return object
     * Customer must present receipt with saleID number
     * @param saleID the sale ID number on the receipt
     * @param reason the reason for returning the product
     */
    public void makeNewSaleReturn(int saleID, String reason) {
        SaleReturn sr = new SaleReturn(saleID, reason);
        currentTransaction=sr;
    }

    /**
     * Creates a new rental return object
     * @param rentalID the rental ID number on the receipt
     */
    public void makeNewRentalReturn(int rentalID) {
        RentalReturn r = new RentalReturn(rentalID);
        currentTransaction = r;
    }

    /**
     * Creates new reimbursement object for a sale return
     * Current transaction accepts and handles reimbursement 
     */
    public void makeReimbursement() {
        Reimbursement r = new Reimbursement();
        currentTransaction.accept(r);
    }

    /**
     * Creates a new credit reimbursement object for a sale return
     * Current transaction accepts and handles reimbursement
     * @param cardNum 
     */
    public void makeCreditReimbursement(String cardNum) {
        CreditReimbursement r = new CreditReimbursement(cardNum);
        this.currentTransaction.accept(r);
    }

    /**
     * At any point if the transaction needs to be canceled
     */
    public void cancelTransaction() {
        this.currentTransaction = null;
    }
    
    /**
     * Get method for user manager
     * @return user manager
     */
    public UserManager getUserManager(){
        return this.userManager;
    }
}
