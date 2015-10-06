import java.util.HashMap;

public class ProductCatalog
{
  private HashMap<ItemID, ProductDescription> catalog;
  
  public ProductCatalog(HashMap<ItemID, ProductDescription> catalog)
  {
    this.catalog = catalog;
  }
  
  public ProductCatalog()
  {
    catalog = new HashMap<ItemID, ProductDescription>();
  }
  
  public HashMap<ItemID, ProductDescription> getCatalog()
  {
    return catalog;
  }
  
  public void add(ProductDescription pd, ItemID id)
  {
    catalog.put(id, pd);
  }
  
  public ProductDescription getProductDescription(ItemID id) {
    return catalog.get(id);
  }
}