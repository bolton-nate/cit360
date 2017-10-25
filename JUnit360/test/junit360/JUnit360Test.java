/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit360;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author natebolton
 */
public class JUnit360Test {
    
    public JUnit360Test() {
    }
    
//    /**
//     * Test of main method, of class JUnit360.
//     */
//    @Test
//    public void testMain() {
//        System.out.println("main");
//        String[] args = null;
//        JUnit360.main(args);
//        // TODO review the generated test code and remove the default call to fail.
//        //fail("The test case is a prototype.");
//    }

    /**
     * Test of validateReportedDaysOff method, of class JUnit360.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testValidateReportedDaysOffNull() {
        //TEST 1
        System.out.println("validateReportedDaysOff - Nulls");
        Date date1 = null;
        Date date2 = new Date();
        long reportedDaysOff = 0L;
        boolean expResult = false;
        thrown.expect(NullPointerException.class);
        thrown.expectMessage("dates can not be null");
        boolean result = JUnit360.validateReportedDaysOff(date1, date2, reportedDaysOff);
    }
        
    @Test
    public void testValidateReportedDaysOff() {
      
        //TEST 2 - Same Days
        System.out.println("validateReportedDaysOff - Same Days");
        Date date1 = new Date();
        Date date2 = new Date();
        long reportedDaysOff = 0L;
        boolean expResult = true;
        boolean result = JUnit360.validateReportedDaysOff(date1, date2, reportedDaysOff);
        assertEquals(expResult, result);

        //TEST 3 - One Day Off
        System.out.println("validateReportedDaysOff - One Day");
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date1 = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date2 = df.parse("05/12/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportedDaysOff = 1L;
        expResult = true;
        result = JUnit360.validateReportedDaysOff(date1, date2, reportedDaysOff);
        assertEquals(expResult, result);
    
        //TEST 4 - 6 Days Off
        System.out.println("validateReportedDaysOff - Six Days");

        try {
            date1 = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date2 = df.parse("05/19/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportedDaysOff = 6L;
        expResult = true;
        result = JUnit360.validateReportedDaysOff(date1, date2, reportedDaysOff);
        assertEquals(expResult, result);
        
        //TEST 5 - minus one Days Off
        System.out.println("validateReportedDaysOff - Negative One Days");

        try {
            date1 = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date2 = df.parse("05/10/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportedDaysOff = -1L;
        expResult = true;
        result = JUnit360.validateReportedDaysOff(date1, date2, reportedDaysOff);
        assertEquals(expResult, result);
        
        //TEST 6 - 1 Days Off, wrong reported day
        System.out.println("validateReportedDaysOff - Wrong Reported Day");

        try {
            date1 = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date2 = df.parse("05/12/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        reportedDaysOff = 2L;
        expResult = false;
        result = JUnit360.validateReportedDaysOff(date1, date2, reportedDaysOff);
        assertEquals(expResult, result);
    }

    /**
     * Test of calculateTotalWorkDays method, of class JUnit360.
     */
    @Test
    public void testCalculateTotalWorkDays() {
        //TEST 1
        System.out.println("calculateTotalWorkDays - 0 days");
        Date start = new Date();
        Date end = new Date();
        long expResult = 0L;
        long result = JUnit360.calculateTotalWorkDays(start, end);
        assertEquals(expResult, result);
        
        //TEST 2
        System.out.println("calculateTotalWorkDays - 1 days");
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            start = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            end = df.parse("05/12/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        expResult = 1L;
        result = JUnit360.calculateTotalWorkDays(start, end);
        assertEquals(expResult, result);
        
        //TEST 3
        System.out.println("calculateTotalWorkDays - 10 days");
        try {
            start = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            end = df.parse("05/25/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        expResult = 10L;
        result = JUnit360.calculateTotalWorkDays(start, end);
        assertEquals(expResult, result);
        
        //TEST 4
        System.out.println("calculateTotalWorkDays - minus 10 days");
        try {
            start = df.parse("05/25/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            end = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        expResult = -10L;
        result = JUnit360.calculateTotalWorkDays(start, end);
        assertEquals(expResult, result);

    }
    
}
