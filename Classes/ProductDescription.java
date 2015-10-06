/**
 * Ben Candell
 * bsc218@lehigh.edu
 * CSE 216
 * ProductDescription class
 */

/**
 * A class to describe a product 
 */
public class ProductDescription {
	private Money price;
	private ItemID id;
	private String description;
	
	public ProductDescription(ItemID id, Money price, String description) {
		this.id=id;
		this.price=price;
		this.description=description;
	}
	
	public ItemID getItemID() { return id; }
	
	public Money getPrice() { return price; }
	
	public String getDescription() { return description; }
}
