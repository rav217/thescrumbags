public class SalesLineItem {
  ProductDescription prod;
  int qty;
  Money subtotal;

  //creates SalesLineItem object given a product description and quantity
  public SalesLineItem(ProductDescription prod, int qty) {
    this.prod = prod;
    this.qty = qty;
    this.subtotal = new Money(prod.getPrice().times(qty));
  }

  //calculates the subtotal of a SalesLineItem object
  public Money getSubtotal() {
    return subtotal;
  }
}
