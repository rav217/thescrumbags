Public class ProductCatalog
{
  private HashMap<ProductDescription, ItemID> catalog;
  
  pubic ProductCatalog(HashMap<ProductDescription, ItemID> catalog)
  {
    this.catalog = catalog;
  }
  
  public ProductCatalog()
  {
    catalog = new HashMap<ProductDescriptionm ItemID>();
  }
  
  public HashMap<ProductDescription, ItemID> getCatalog()
  {
    return catalog;
  }
  
  public void add(ProductDescription pd, ItemID id = NULL)
  {
    catalog.put(pd, id);
  }
}