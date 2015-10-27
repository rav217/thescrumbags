/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

/**
 *
 * @author Ben Candell
 */
public class HandleProcessRental {

    public static void doProcessRental() {
        Store store = Store.getInstance();

        Money m1 = new Money(100.0);
        Money m2 = new Money(50.0);
        Money m3 = new Money(25.0);
        int prodID1 = 1;
        int prodID2 = 2;
        int prodID3 = 3;
        ProductDescription pd1 = new ProductDescription(prodID1, m1, "shirt");
        ProductDescription pd2 = new ProductDescription(prodID2, m2, "shorts");
        ProductDescription pd3 = new ProductDescription(prodID3, m3, "pants");
        
        store.getCatalog().add(pd3, prodID3);
        store.getCatalog().add(pd2, prodID2);
        store.getCatalog().add(pd1, prodID1);
        
        Register reg=store.getRegister();
        reg.makeNewTransaction();
        
    }
}
