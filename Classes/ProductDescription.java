package thescrumbags.Classes;

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
	private int id;
	private String description;
	
	public ProductDescription(int id, Money price, String description) {
		this.id=id;
		this.price=price;
		this.description=description;
	}
	
	public int getItemID() { return id; }
	
	public Money getPrice() { return price; }
	
	public String getDescription() { return description; }
}
