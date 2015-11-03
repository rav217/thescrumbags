package thescrumbags.Classes;

/*
 * Ben Candell
 * bsc218@lehigh.edu
 * CSE 216
 * Register class
 */
import java.util.*;

/*
 * A singleton class representing a cash register for the point-of-sale system
 */
public class Register {

    private ProductCatalog catalog;
    private Transaction currentTransaction;
    private Payment currentPayment;
    private boolean isOpen;
    private DBHandler dbHandler;
    private UserManager userManager;
    private static Register uniqueInst;

    /**
     * Default constructor: pulls product catalog and employee records from db
     */
    private Register() { } //default constructor for now

    /**
     * Constructor, assigns field values for Register object If no Sale
     * parameter passed, currentSale is set to null
     *
     * @param catalog the Store's product catalog
     */
    
    /*public Register(ProductCatalog catalog) {
        this.catalog = catalog;
    }*/
    
    public static synchronized Register getInstance(){
        if (uniqueInst == null){
            uniqueInst = new Register();
        }
        return uniqueInst;
    }

    /*public void initializeData(){
        //open new db connection
        dbHandler = DBHandler.getInstance();
        dbHandler.openConnection("sql595207", "nT1*rF4!");
        
        //fetch ProductCatalog and EmployeeList from db
        this.catalog = dbHandler.initSPC();
        //this.rentalcatalog = dbHandler.initRPC();
        dbHandler.closeConnection();
    }*/

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public Payment getCurrentPayment() {
        return this.currentPayment;
    }
    
    public void setCurrentPayment(Payment p) {
        this.currentPayment = p;
    }
            
    public ProductCatalog getCatalog() {
        return catalog;
    }

    /**
     * Creates a new Sale object and stores it in currentSale
     */
    public void makeNewSale() {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.catalog = db.initSPC();
        db.closeConnection();
        this.currentTransaction = new Sale();
    }

    public void makeNewRental(int numDays) {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.catalog = db.initRPC();
        db.closeConnection();
        this.currentTransaction = new Rental(numDays);
    }

    //public void makeNewReturn() {
    //    this.currentTransaction = new Return();
    //}
    
    public void endTransaction() {
        currentTransaction.updateInventory();
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

    public void makeNewSaleReturn(int saleID, String reason) {
        SaleReturn s = new SaleReturn(saleID, reason);
        this.currentTransaction = s;
        makeReimbursement();
    }

    public void makeNewRentalReturn(int rentalID) {
        RentalReturn r = new RentalReturn(rentalID);
        this.currentTransaction = r;
    }

    public void makeReimbursement() {
        Reimbursement r = new Reimbursement();
        this.currentTransaction.accept(r);
    }

    public void makeCreditReimbursement(String cardNum) {
        CreditReimbursement r = new CreditReimbursement(cardNum);
        this.currentTransaction.accept(r);
    }

    public void cancelTransaction() {
        this.currentTransaction = null;
    }
    
    public UserManager getUserManager(){
        return this.userManager;
    }
}
