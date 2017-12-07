/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.LinkedList;
import model.*;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author natebolton
 */
public class HibernateControl {

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    

    
    public String getPassword(String uname) {
        try {
            System.out.println("Creating SessionFactory...");
            factory = HibernateControl.createSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to get session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
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
        System.out.println("Closing SessionFactory...");
        factory.close();
        return pword;
    }
    
    public Employees getEmployeeByUsername(String uname) {
        try {
            System.out.println("Creating SessionFactory...");
            factory = HibernateControl.createSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to get session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        
        Employees employee = null;
        int uid = 0;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("SELECT E.emp_id FROM model.EmployeeAuth E WHERE E.username = :uname");
            query.setParameter("uname", uname);
            List idList = query.list();
            //System.out.print("pwList is: " + pwList);
            if (!idList.isEmpty()) {
                uid = (int) idList.get(0);
            }
            employee = (Employees) session.get(Employees.class, uid);
            Hibernate.initialize(employee.getEmployeeType().getTypeTitle());
            //System.out.println("HIBERNATE GOT:  " + employee.toString());
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Closing SessionFactory...");
        factory.close();
        return employee;
    }
    
    public List getRequestsByUid(int uid) {
        try {
            System.out.println("Creating SessionFactory...");
            factory = HibernateControl.createSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to get session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        List rlist = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("FROM model.Requests WHERE emp_id = :empID ");
            query.setParameter("empID", uid);
            rlist = query.list();
            System.out.println("HIBERNATE GOT:  " + rlist.get(0).toString());
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        System.out.println("Closing SessionFactory...");
        factory.close();
        return rlist;
    }
    
    public boolean insertRequest(Requests request) {
        try {
            System.out.println("Creating SessionFactory...");
            factory = HibernateControl.createSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to get session factory. " + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(request);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
        System.out.println("Closing SessionFactory...");
        factory.close();
        return true;
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
    
    public static ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public static void setServiceRegistry(ServiceRegistry serviceRegistry) {
        HibernateControl.serviceRegistry = serviceRegistry;
    }
}
