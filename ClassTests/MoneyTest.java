/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.math.BigDecimal;
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
public class MoneyTest {
    
    public MoneyTest() {
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
     * Test of getAmount method, of class Money.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        Money instance = new Money();
        BigDecimal expResult = new BigDecimal(0);
        BigDecimal result = instance.getAmount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Money.
     */
    @Test
    public void testAdd_Money() {
        System.out.println("add");
        BigDecimal num = new BigDecimal(2);
        Money m = new Money(num);
        Money instance = new Money();
        Money expResult = instance;
        Money result = instance.add(m);
        assertEquals(expResult, result);
        assertEquals(expResult.getAmount(), num);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class Money.
     */
    @Test
    public void testAdd_BigDecimal() {
        System.out.println("add");
        BigDecimal num = new BigDecimal(0);
        BigDecimal num2 = new BigDecimal(2);
        Money instance = new Money(num2);
        Money expResult = instance;
        Money result = instance.add(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class Money.
     */
    @Test
    public void testSubtract_Money() {
        System.out.println("subtract");
        BigDecimal num = new BigDecimal(2);
        Money m = new Money(num);
        Money instance = new Money();
        Money expResult = instance;
        Money result = instance.subtract(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of subtract method, of class Money.
     */
    @Test
    public void testSubtract_BigDecimal() {
        System.out.println("subtract");
        BigDecimal num = new BigDecimal(0);
        BigDecimal num2 = new BigDecimal(2);
        Money instance = new Money(num2);
        Money expResult = instance;
        Money result = instance.subtract(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of multiply method, of class Money.
     */
    @Test
    public void testMultiply() {
        System.out.println("multiply");
        BigDecimal num = new BigDecimal(2);
        Money m = new Money(num);
        Money instance = new Money();
        Money expResult = instance;
        Money result = instance.multiply(num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Money.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Money o = new Money();
        Money instance = new Money();
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of compareTo method, of class Money.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        BigDecimal num = new BigDecimal(0);
        Money o = new Money(num);
        Money instance = new Money();
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
