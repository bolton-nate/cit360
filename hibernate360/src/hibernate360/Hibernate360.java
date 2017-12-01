/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate360;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import model.*;
import view.*;
import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
/**
 *
 * @author natebolton
 */
public class Hibernate360 {

    /**
     * @param args the command line arguments
     */
    
    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    private static PrintWriter outFile = null;
    private static BufferedReader inFile = null;
    
    public static void main(String[] args) {
        try {
            System.out.println("Creating SessionFactory...");
            factory = Hibernate360.createSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Failed to get session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        //System.out.println("finished with factory creation");
        try {
            
            Hibernate360.inFile = new BufferedReader(new InputStreamReader(System.in));
            Hibernate360.outFile = new PrintWriter(System.out, true);
            
            Login login = new Login();
            login.display();
//            MainMenu mainMenu = new MainMenu();
//            mainMenu.display();
        } catch (Throwable te) {
            System.out.println("This program has encountered an error and will close.");
            System.out.println(te.getMessage());
            te.printStackTrace();
            System.exit(0);
        }
        
//        Hibernate360 hb360 = new Hibernate360();
//        Employees employee = hb360.addEmployees("test1","test2",25);
//        hb360.listEmployees();
    }
    
    public Employees addEmployees(String fName, String lName, int empType) {
        Session session = factory.openSession();
        Employees employee = null;
        Transaction tx = null;
        Integer empID = null;
        try {
            tx = session.beginTransaction();
            EmployeeType employeeType = new EmployeeType(empType);
            employee = new Employees(employeeType,lName,fName,null,null);
            empID = (Integer) session.save(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return employee;
    }
    
    public void removeEmployee(Integer empID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Employees employee = null;
        try {
            tx = session.beginTransaction();
            employee = (Employees) session.load(Employees.class, empID);
            if (employee != null) {
                try {
                    session.delete(employee);
                } catch(ObjectNotFoundException ex) {
                    System.out.println("\n\nThat employee does not exist in the database");
                }
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void listEmployees( ){
        //Get the session from the session factory.
        Session session = factory.openSession();
        Transaction tx = null;
        try{
           tx = session.beginTransaction();
           //Make an HQL query to get the results from books table . You can also use SQL here.
           List employeesList = session.createQuery("FROM model.Employees").list();
           //Iterate over the result and print it.
           for (Iterator iterator = employeesList.iterator(); iterator.hasNext();){
                Employees employee = (Employees) iterator.next();
                System.out.print("ID: " + employee.getEmpId());
                System.out.print("\tfirst name: " + employee.getFirstName());
                System.out.print("\tlast name: " + employee.getLastName());
                System.out.print("\t\temployee type: " + employee.getEmployeeType());
                System.out.println("\n");
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
           session.close();
        }
     }
    
    public static String getPassword(String uname) {
        Session session = factory.openSession();
        Transaction tx = null;
        String pword = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT E.password FROM model.EmployeeAuth E WHERE E.username = :uname");
            query.setParameter("uname", uname);
            List pwList = query.list();
            //System.out.print("pwList is: " + pwList);
            if (!pwList.isEmpty()) {
                pword = pwList.get(0).toString();
            }
            //System.out.print("pword in getPassword is: " + pword);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return pword;
    }
    
    public static boolean authenticateUser(String uname, String pword) {
        String pwordCheck = Hibernate360.getPassword(uname);
        //System.out.println("pwordCheck is: " + pwordCheck);
        if (pword.equals(pwordCheck)) {
            return true;
        }
        
        return false;
    }
    
    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void setFactory(SessionFactory factory) {
        Hibernate360.factory = factory;
    }

    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
        Hibernate360.serviceRegistry = serviceRegistry;
    }

    public static PrintWriter getOutFile() {
        return outFile;
    }

    public static void setOutFile(PrintWriter outFile) {
        Hibernate360.outFile = outFile;
    }

    public static BufferedReader getInFile() {
        return inFile;
    }

    public static void setInFile(BufferedReader inFile) {
        Hibernate360.inFile = inFile;
    }
    
    
}
