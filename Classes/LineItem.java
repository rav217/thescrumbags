package thescrumbags.Classes;

import java.math.BigDecimal;
import java.util.*;

public class LineItem {

    private ProductDescription prod;
    private int qty;
    private Money subtotal;

    //creates LineItem object given a product description and quantity
    public LineItem(ProductDescription prod, int qty) {
        this.prod = prod;
        this.qty = qty;
        BigDecimal q = new BigDecimal(qty);
        System.out.println(q);
        System.out.println(prod);
        BigDecimal bd = prod.getPrice().getAmount().multiply(q);
        System.out.println(bd);
        this.subtotal = new Money(bd);
        System.out.println(this.subtotal);
    }
    
    // constructor for LineItems added by Jacob
    public LineItem(LineItem li) {
        this.prod = li.prod;
        this.qty = li.qty;
        this.subtotal = li.subtotal;
    }
    
    //constructor with subtotal added by chris
    public LineItem(ProductDescription prod, int qty, Money subtotal) {
        this.prod = prod;
        this.qty = qty;
        this.subtotal = subtotal;
    }

    //calculates the subtotal of a LineItem object
    public Money getSubtotal() {
        return new Money(this.subtotal.getAmount());
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
    
    @Override
    public String toString() {
        String str=String.format("%-20s %d%s%-12s %s%-10s", prod.getDescription(), qty,"x$", prod.getPrice().toString(), "$", this.getSubtotal().toString() );
        return str;
    }
}
