package thescrumbags.Classes;

/*
 * Ben Candell
 * bsc218@lehigh.edu
 * CSE 216
 * Register class
 */
import java.util.*;

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
     * Default constructor: pulls product catalog and employee records from db
     */
    public Register() {
        //open new db connection
        dbHandler = DBHandler.getInstance();
        dbHandler.openConnection("sql595207", "nT1*rF4!");
        
        //fetch ProductCatalog from db conn
        dbHandler.initProductCatalog(this);
        
        //fetch EmployeeList from db conn
        /*EmployeeList tempEL = new EmployeeLsit();
        this.
        db.closeConnection();*/
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

    public void makeNewRental(GregorianCalendar returnDate) {
        this.currentTransaction = new Rental(returnDate);
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
        endTransaction();
    }
}
