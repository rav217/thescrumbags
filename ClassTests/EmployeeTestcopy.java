/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

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
public class EmployeeTest {
    
    public EmployeeTest() {
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
     * Test of getEmployeeName method, of class Employee.
     */
    @Test
    public void testGetEmployeeName() {
        System.out.println("getEmployeeName");
        Employee instance = new Employee();
        String expResult = "unknown name";
        String result = instance.getEmployeeName();
        assertEquals(expResult, result);
        System.out.println(instance.getEmployeeName());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeID method, of class Employee.
     */
    @Test
    public void testGetEmployeeID() {
        System.out.println("getEmployeeID");
        Employee instance = new Employee();
        int expResult = 0;
        int result = instance.getEmployeeID();
        assertEquals(expResult, result);
        System.out.println(instance.getEmployeeID());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of correctPassword method, of class Employee.
     */
    @Test
    public void testCorrectPassword() {
        System.out.println("correctPassword");
        String password = "defaultpassword";
        Employee instance = new Employee();
        Boolean expResult = true;
        Boolean result = instance.correctPassword(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of printEmployee method, of class Employee.
     */
    @Test
    public void testPrintEmployee() {
        System.out.println("printEmployee");
        Employee instance = new Employee();
        instance.printEmployee();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isManager method, of class Employee.
     */
    @Test
    public void testIsManager() {
        System.out.println("isManager");
        Employee instance = new Employee();
        Boolean expResult = false;
        Boolean result = instance.isManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
