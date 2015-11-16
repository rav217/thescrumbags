package thescrumbags.Classes;

/**
 *
 * @author The Scrumbags
 */
public class SaleReceipt extends Receipt {
    public SaleReceipt() {}
    
    /**
     * Writes the actual receipt body.
     * @param t
     * @throws ClassCastException 
     */
    @Override
    public void makeReceiptBody(Transaction t) throws ClassCastException {
        if(t instanceof Sale) { 
            Sale s=(Sale)t;
            receiptBody+="SCRUMBAGS POS\t"+date.toString()+"\n\n";
            for (int i=0; i < s.getLineItemsLength(); i++) {
                receiptBody+=s.getLineItem(i).toString()+"\n";
            }
        } else {
            throw new ClassCastException();
        }
    }
}
