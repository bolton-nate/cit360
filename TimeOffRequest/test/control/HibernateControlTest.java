/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import model.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author natebolton
 */
public class HibernateControlTest {
    
    public HibernateControlTest() {
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
     * Test of getPassword method, of class HibernateControl.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String uname = "This username should not have a password!";
        HibernateControl instance = new HibernateControl();
        assertNull(instance.getPassword(uname));
        
        uname = "admin";
        assertNotNull(instance.getPassword(uname));
        
        String pw = "admin";
        String result = instance.getPassword(uname);
        assertEquals(pw, result);

    }

    /**
     * Test of getEmployeeByUsername method, of class HibernateControl.
     */
    @Test
    public void testGetEmployeeByUsername() {
        System.out.println("getEmployeeByUsername");
        String uname = "admin";
        HibernateControl instance = new HibernateControl();
        Employees result = instance.getEmployeeByUsername(uname);
        Integer expResult = 1;
        assertEquals(expResult, result.getEmpId());
        
        uname = "user";
        result = instance.getEmployeeByUsername(uname);
        expResult = 1;
        assertNotEquals(expResult, result.getEmpId());
        //assertNull(result.getRequestses());
        
        
        

    }

}
