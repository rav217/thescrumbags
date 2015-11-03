package thescrumbags.Classes;

import java.math.BigDecimal;

public class LineItem {

    private ProductDescription prod;
    private int qty;
    private Money subtotal;

    //creates LineItem object given a product description and quantity
    public LineItem(ProductDescription prod, int qty) {
        this.prod = prod;
        this.qty = qty;
        BigDecimal q=new BigDecimal(qty);
        BigDecimal bd = prod.getPrice().getAmount().multiply(q);
        this.subtotal = new Money(bd);
    }

    //calculates the subtotal of a LineItem object
    public Money getSubtotal() {
        return this.subtotal;
    }

    public int getQuantity(){ //recently added by chris
        return this.qty;
    }
    
    public ProductDescription getProductDescription() {
        return this.prod;
    }

    public void print() {
        //display LineItem info
        System.out.printf(": %s\tPrice: $%4.2f\tQuantity: %d\tSubtotal: $%4.2f\n", this.prod.getDescription(), this.prod.getPrice().getAmount(), this.qty, this.subtotal.getAmount());
    }
}
