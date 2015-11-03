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
public class EmployeeListTest {
    
    public EmployeeListTest() {
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
     * Test of addEmployee method, of class EmployeeList.
     */
    @Test
    public void testAddEmployee() {
        System.out.println("addEmployee");
        Employee newEmployee = new Employee();
        EmployeeList instance = new EmployeeList();
        instance.addEmployee(newEmployee);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeEmployee method, of class EmployeeList.
     */
    @Test
    public void testRemoveEmployee() {
        System.out.println("removeEmployee");
        int employeeID = 1;
        Employee newEmployee = new Employee(employeeID);
        EmployeeList instance = new EmployeeList();
        instance.addEmployee(newEmployee);
        instance.removeEmployee(employeeID);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of checkPassword method, of class EmployeeList.
     */
    @Test
    public void testCheckPassword() {
        System.out.println("checkPassword");
        int employeeID = 1;
        String password = "";
        Employee newEmployee = new Employee(employeeID);
        EmployeeList instance = new EmployeeList();
        instance.addEmployee(newEmployee);
        Boolean expResult = false;
        Boolean result = instance.checkPassword(employeeID, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployeeCount method, of class EmployeeList.
     */
    @Test
    public void testGetEmployeeCount() {
        System.out.println("getEmployeeCount");
        Employee newEmployee = new Employee();
        EmployeeList instance = new EmployeeList();
        instance.addEmployee(newEmployee);
        int expResult = 1;
        int result = instance.getEmployeeCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isEmployee method, of class EmployeeList.
     */
    @Test
    public void testIsEmployee() {
        System.out.println("isEmployee");
        int employeeID = 1;
        Employee newEmployee = new Employee(employeeID);
        EmployeeList instance = new EmployeeList();
        instance.addEmployee(newEmployee);
        Boolean expResult = true;
        Boolean result = instance.isEmployee(employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findEmployee method, of class EmployeeList.
     */
    @Test
    public void testFindEmployee() {
        System.out.println("findEmployee");
        int employeeID = 1;
        Employee newEmployee = new Employee(employeeID);
        EmployeeList instance = new EmployeeList();
        instance.addEmployee(newEmployee);
        Employee expResult = newEmployee;
        Employee result = instance.findEmployee(employeeID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of printList method, of class EmployeeList.
     */
    @Test
    public void testPrintList() {
        System.out.println("printList");
        EmployeeList instance = new EmployeeList();
        Employee newEmployee = new Employee(0);
        instance.addEmployee(newEmployee);
        Employee newEmployee1 = new Employee(1);
        instance.addEmployee(newEmployee1);
        Employee newEmployee2 = new Employee(2);
        instance.addEmployee(newEmployee2);
        instance.printList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
