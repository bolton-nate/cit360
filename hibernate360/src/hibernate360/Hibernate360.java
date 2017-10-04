/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate360;
import model.*;
import java.util.*;
import org.hibernate.HibernateException;
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
    public static void main(String[] args) {
        try {
            factory = Hibernate360.createSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Failed to get session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        Hibernate360 hb360 = new Hibernate360();
        Employees employee = hb360.addEmployees("test1","test2",25);
        hb360.listEmployees();
    }
    
    public Employees addEmployees(String fName, String lName, int empType) {
        Session session = factory.openSession();
        Employees employee = null;
        Transaction tx = null;
        Integer empID = null;
        try {
            tx = session.beginTransaction();
            employee = new Employees(fName,lName,empType);
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
                System.out.print("\temployee type: " + employee.getEmpType());
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
    
    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
    
}
