package thescrumbags.Classes;

/**
 * Ben Candell bsc218@lehigh.edu CSE 216 ProductDescription class
 */
/**
 * A class to describe a product
 */
public class ProductDescription {

    private final Money price;
    private final int id;
    private final String description;

    public ProductDescription(int id, Money price, String description) {
        this.id = id;
        this.price = price;
        this.description = description;
    }

    public int getItemID() {
        return id;
    }

    public Money getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    
    public boolean equals(ProductDescription pd) { 
        return pd.getItemID()==id;
    }
}
