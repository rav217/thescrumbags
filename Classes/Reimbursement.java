package thescrumbags.Classes;

/**
 * To be used for all cash reimbursements
 * CreditReiumbursement will extend Reimbursement
 * @author The Scrumbags
 */
public class Reimbursement implements Payment {
    
    private Money amount;
    
    /**
     * Default constructor for Reimbursement
     */
    public Reimbursement() {}
    
    /**
     * Get method for amount
     * @return the amount of money to be reimbursed
     */
    public Money getAmount() { return amount; } 
    
    /**
     * Prompts cashier to give the customer a certain amount.
     * First negates the total to represent an outflow of money.
     * @param s the SaleReturn to be verified
     * @return success/failure of method
     */
    @Override
    public boolean verify(Transaction t) {
        if(t instanceof SaleReturn) {
            SaleReturn s=(SaleReturn)t;
            amount=s.negateTotal();
            System.out.println("Give the customer $"+amount); //will show negative number
            return true;
        }
        return false;
    }
}
