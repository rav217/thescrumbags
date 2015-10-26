package thescrumbags.Classes;

public class SalesLineItem {
  ProductDescription prod;
  int qty;
  Money subtotal;

  //creates SalesLineItem object given a product description and quantity
  public SalesLineItem(ProductDescription prod, int qty) {  
    this.prod = prod;
    this.qty = qty;
    double subtotalDouble = prod.getPrice().getAmount() * (double)qty;
    this.subtotal = new Money(subtotalDouble);
  }

  //calculates the subtotal of a SalesLineItem object
  public Money getSubtotal() {
    return this.subtotal;
  }
}
