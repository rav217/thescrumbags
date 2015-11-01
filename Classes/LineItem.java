package thescrumbags.Classes;

public class LineItem {
  ProductDescription prod;
  int qty;
  Money subtotal;

  //creates LineItem object given a product description and quantity
  public LineItem(ProductDescription prod, int qty) {  
    this.prod = prod;
    this.qty = qty;
    double subtotalDouble = prod.getPrice().getAmount() * (double)qty;
    this.subtotal = new Money(subtotalDouble);
  }

  //calculates the subtotal of a LineItem object
  public Money getSubtotal() {
    return this.subtotal;
  }
  
  public ProductDescription getProductDescription() {
      return this.prod;
  }
  
  public void print() {
  //display LineItem info
      System.out.printf(": %s\tPrice: $%4.2f\tQuantity: %d\tSubtotal: $%4.2f\n", this.prod.getDescription(), this.prod.getPrice().getAmount(), this.qty, this.subtotal.getAmount());
  }
}
