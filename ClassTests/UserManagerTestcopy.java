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
public class UserManagerTest {
    
    public UserManagerTest() {
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
     * Test of addCashier method, of class UserManager.
     */
    @Test
    public void testAddCashier() {
        System.out.println("addCashier");
        String name = "name";
        String password = "password";
        UserManager instance = new UserManager();
        instance.addCashier(name, password);
        instance.getEmployeeList().printList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addManager method, of class UserManager.
     */
    @Test
    public void testAddManager() {
        System.out.println("addManager");
        String name = "name";
        String password = "password";
        UserManager instance = new UserManager();
        String name2 = "name2";
        String password2 = "password2";
        instance.getEmployeeList().printList();
        instance.addCashier(name, password);
        instance.addManager(name2, password2);
        instance.getEmployeeList().printList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of removeEmployee method, of class UserManager.
     */
    @Test
    public void testRemoveEmployee() {
        System.out.println("removeEmployee");
        int employeeID = 1;
        UserManager instance = new UserManager();
        String name = "name";
        String password = "password";
        String name2 = "name2";
        String password2 = "password2";
        instance.addCashier(name, password);
        instance.addManager(name2, password2);
        instance.getEmployeeList().printList();
        instance.removeEmployee(employeeID);
        System.out.println("REMOVING 1");
        instance.getEmployeeList().printList();
        System.out.println("REMOVING 3 [NONMEMBER]");
        instance.removeEmployee(3);
        instance.getEmployeeList().printList();
        System.out.println("REMOVING 2");
        instance.removeEmployee(2);
        instance.getEmployeeList().printList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class UserManager.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        UserManager instance = new UserManager();
        String name = "name";
        String password = "password";
        String name2 = "name2";
        String password2 = "password2";
        instance.addCashier(name, password);
        instance.addManager(name2, password2);
        System.out.println("BEFORE LOGINS");
        instance.getLoggedOnList().printList();
        instance.login(1, password);
        System.out.println("LOGIN 1");
        instance.getLoggedOnList().printList();
        System.out.println("LOGIN 2 [FAIL PASSWORD]");
        instance.login(2, password);
        instance.getLoggedOnList().printList();
        System.out.println("LOGIN 2 [CORRECT PASSWORD]");
        instance.login(2, password2);
        instance.getLoggedOnList().printList();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class UserManager.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        UserManager instance = new UserManager();
        String name = "name";
        String password = "password";
        String name2 = "name2";
        String password2 = "password2";
        instance.addCashier(name, password);
        instance.addManager(name2, password2);
        instance.login(1, password);
        instance.login(2, password2);
        System.out.println("BEFORE LOGOUTS");
        instance.getLoggedOnList().printList();
        System.out.println("LOGOUT 1");
        instance.logout(1);
        instance.getLoggedOnList().printList();
        System.out.println("LOGOUT 3 [NON MEMBER]");
        instance.logout(3);
        instance.getLoggedOnList().printList();
        System.out.println("LOGOUT 2");
        instance.logout(2);
        instance.getLoggedOnList().printList();
        
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class UserManager.
     */
    
}
