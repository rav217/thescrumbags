import java.util.HashMap;

public class ProductCatalog
{
  private HashMap<Integer, ProductDescription> catalog;
  
  public ProductCatalog(HashMap<Integer, ProductDescription> catalog)
  {
    this.catalog = catalog;
  }
  
  public ProductCatalog()
  {
    catalog = new HashMap<Integer, ProductDescription>();
  }
  
  public HashMap<Integer, ProductDescription> getCatalog()
  {
    return catalog;
  }
  
  public void add(ProductDescription pd, Integer id)
  {
    catalog.put(id, pd);
  }
  
  public ProductDescription getProductDescription(Integer id) {
    return catalog.get(id);
  }
}