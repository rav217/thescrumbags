package thescrumbags.Classes;

/**
 * A class to represent a receipt for a rental.
 * Extends Receipt
 * @author The Scrumbags
 */
public class RentalReceipt extends Receipt {
    public RentalReceipt() { super(); }
    
     /**
     * Writes the actual receipt body.
     * @param t
     * @throws ClassCastException 
     */
    @Override
    public void makeReceiptBody(Transaction t) throws ClassCastException { 
        super.makeReceiptBody(t);
        try{
            if(t instanceof Rental) {
                Rental r=(Rental)t;
                receiptBody+="\n\nTHIS IS A RENTAL. YOU HAVE UNTIL\n" + r.getReturnDate().getTime().toString();
                receiptBody+="\nTO RETURN THESE PRODUCTS.";
            } else {
                throw new ClassCastException();
            }
        }
        catch (ClassCastException ex) {
            System.out.println("Class cast error");
            ex.printStackTrace();
        }
    } 
}
