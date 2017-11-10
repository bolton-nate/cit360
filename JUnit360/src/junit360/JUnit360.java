/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junit360;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author natebolton
 */
public class JUnit360 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date date1 = new Date();
        Date date2 = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        try {
            date1 = df.parse("05/11/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            date2 = df.parse("05/09/2017");
        } catch (ParseException ex) {
            Logger.getLogger(JUnit360.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println(JUnit360.validateReportedDaysOff(date1, date2, 6));
        } catch (NullPointerException ex) {
            System.out.println("Error:" + ex);
        }
    }
    
    public static boolean validateReportedDaysOff(Date date1, Date date2, long reportedDaysOff) {
        if (date1 == null || date2 == null) {
            throw new NullPointerException("dates can not be null");
        }
        if (JUnit360.calculateTotalWorkDays(date1, date2) == reportedDaysOff) {
            return true;
        }
        return false;
    }
    
    public static long calculateTotalWorkDays(Date start, Date end) {
        //copied from https://stackoverflow.com/questions/4600034/calculate-number-of-weekdays-between-two-dates-in-java/44942039#44942039
        //argument check
        if (start == null || end == null) {
            throw new NullPointerException("dates can not be null");
        }

        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        //end Saturday to start Saturday 
        long days = (c2.getTimeInMillis()-c1.getTimeInMillis())/(1000*60*60*24);
        long daysWithoutWeekendDays = days-(days*2/7);

        // Adjust days to add on (w2) and days to subtract (w1) so that Saturday
        // and Sunday are not included
        if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
            w1 = Calendar.MONDAY;
        } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
            w1 = Calendar.FRIDAY;
        } 

        if (w2 == Calendar.SUNDAY) {
            w2 = Calendar.MONDAY;
        } else if (w2 == Calendar.SATURDAY) {
            w2 = Calendar.FRIDAY;
        }

        System.out.println(daysWithoutWeekendDays-w1+w2);
        return daysWithoutWeekendDays-w1+w2;
    }
    
    public static Integer testNull() {
        return null;
    }
    
    public static int[] testArray() {
        
        return new int[]{1,2,3}; 
    }
}
