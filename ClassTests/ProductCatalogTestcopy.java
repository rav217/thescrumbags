/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.math.BigDecimal;
import java.util.HashMap;
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
public class ProductCatalogTest {
    
    public ProductCatalogTest() {
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
     * Test of getCatalog method, of class ProductCatalog.
     */
    @Test
    public void testGetCatalog() {
        System.out.println("getCatalog");
        ProductCatalog instance = new ProductCatalog();
        HashMap<Integer, ProductDescription> expResult = new HashMap<Integer, ProductDescription>();
        HashMap<Integer, ProductDescription> result = instance.getCatalog();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ProductCatalog.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription pd = new ProductDescription(12, m, "things");
        ProductCatalog instance = new ProductCatalog();
        instance.add(pd);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getProductDescription method, of class ProductCatalog.
     */
    @Test
    public void testGetProductDescription() {
        System.out.println("getProductDescription");
        Integer id = 12;
        ProductCatalog instance = new ProductCatalog();
        BigDecimal num = new BigDecimal(10);
        Money m = new Money(num);
        ProductDescription desc = new ProductDescription(id, m, "things");
        instance.add(desc);
        ProductDescription expResult = desc;
        ProductDescription result = instance.getProductDescription(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
