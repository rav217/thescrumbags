/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bobby
 */
public class TransactionTest {
    
    public TransactionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of becomeComplete method, of class Transaction.
     */
    @Test
    public void testBecomeComplete() {
        System.out.println("becomeComplete");
        Transaction instance = new Transaction();
        instance.becomeComplete();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isComplete method, of class Transaction.
     */
    @Test
    public void testIsComplete() {
        System.out.println("isComplete");
        Transaction instance = new Transaction();
        boolean expResult = false;
        boolean result = instance.isComplete();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of makeLineItem method, of class Transaction.
     */
    @Test
    public void testMakeLineItem() {
        System.out.println("makeLineItem");
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(12, m, "things");
        int qty = 10;
        Transaction instance = new Transaction();
        LineItem result = instance.makeLineItem(desc, qty);
        LineItem expResult = result;
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeLineItem method, of class Transaction.
     */
    @Test
    public void testRemoveLineItem() {
        System.out.println("removeLineItem");
        int index = 1;
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(12, m, "things");
        int qty = 10;
        Transaction instance = new Transaction();
        LineItem result = instance.makeLineItem(desc, qty);
        LineItem result2 = instance.makeLineItem(desc, 11);
        instance.removeLineItem(index);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLineItem method, of class Transaction.
     */
    @Test
    public void testGetLineItem() {
        System.out.println("getLineItem");
        int index = 0;
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(12, m, "things");
        int qty = 10;
        Transaction instance = new Transaction();
        LineItem result = instance.makeLineItem(desc, qty);
        LineItem result2 = instance.makeLineItem(desc, 11);
        LineItem expResult = result;
        LineItem result3 = instance.getLineItem(index);
        assertEquals(expResult, result3);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLineItems method, of class Transaction.
     */
    @Test
    public void testGetLineItems() {
        System.out.println("getLineItems");
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(12, m, "things");
        int qty = 10;
        Transaction instance = new Transaction();
        LineItem result = instance.makeLineItem(desc, qty);
        //LineItem result2 = instance.makeLineItem(desc, 11);
        ArrayList<LineItem> expResult = new ArrayList<LineItem>();
        expResult.add(result);
        ArrayList<LineItem> result3 = instance.getLineItems();
        assertEquals(expResult, result3);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getLastLineItem method, of class Transaction.
     */
    @Test
    public void testGetLastLineItem() {
        System.out.println("getLastLineItem");
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(12, m, "things");
        int qty = 10;
        Transaction instance = new Transaction();
        LineItem result = instance.makeLineItem(desc, qty);
        LineItem result2 = instance.makeLineItem(desc, 11);
        LineItem result3 = instance.makeLineItem(desc, 11);
        LineItem expResult = result3;
        LineItem result4 = instance.getLastLineItem();
        assertEquals(expResult, result4);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal method, of class Transaction.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(12, m, "things");
        int qty = 1;
        Transaction instance = new Transaction();
        LineItem result = instance.makeLineItem(desc, qty);
        LineItem result2 = instance.makeLineItem(desc, 2);
        LineItem result3 = instance.makeLineItem(desc, 3);
        BigDecimal num2 = new BigDecimal(60);
        Money expResult = new Money(num2);
        Money result4 = instance.getTotal();
        assertEquals(expResult, result4);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class Transaction.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        BigDecimal num2 = new BigDecimal(60);
        Money m = new Money(num2);
        Transaction instance = new Transaction();
        instance.setTotal(m);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of accept method, of class Transaction.
     */
    @Test
    public void testAccept_Payment() {
        System.out.println("accept");
        Payment p = null;
        Transaction instance = new Transaction();
        instance.accept(p);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of accept method, of class Transaction.
     */
    @Test
    public void testAccept_Reimbursement() {
        System.out.println("accept");
        Reimbursement r = null;
        Transaction instance = new Transaction();
        instance.accept(r);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of updateInventory method, of class Transaction.
     */
    @Test
    public void testUpdateInventory() {
        System.out.println("updateInventory");
        Transaction instance = new Transaction();
        instance.updateInventory();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
