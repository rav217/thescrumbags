//Store class: singleton class containing ProductCatalog and Register attributes

public class Store{
	
	private static Store uniqueInst;
	private ProductCatalog catalog;
	private Register register;
	private ArrayList<Employee> employees;
	
	//singleton getInstance() method
	public static synchronized Store getInstance(){
		if (uniqueInst == null)
			uniqueInst = new Store();
		return uniqueInst;
	}
	//constructor
	public Store()
		this.catalog = new ProductCatalog();
		this.register = new Register(catalog);
		this.employees = new ArrayList<Employee>;
	}
	
	//get catalog
	public getCatalog(){
		return this.catalog;
	}
	
	//get register
	public getRegister(){
		return this.register;
	}
	
	//get employee list
	public getEmployees(){
		return this.employees;
	}
}