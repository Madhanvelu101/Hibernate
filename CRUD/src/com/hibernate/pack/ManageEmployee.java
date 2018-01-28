package com.hibernate.pack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transaction;



import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageEmployee {
	private static SessionFactory factory;
	public static void main(String[] args) {
		try {
			factory=new Configuration().configure().buildSessionFactory();
			
		} catch (Throwable e) {
			
			System.err.println("Failed to create sessionFactory object" +e);
			throw new ExceptionInInitializerError(e);
		}
		ManageEmployee ME=new ManageEmployee();
		//add
		HashSet set1=new HashSet();
		set1.add(new Certificate("BE"));  
		set1.add(new Certificate("MBA"));
		Integer emp1=ME.addEmployee("Sudhan","Kumar",70000,set1);
		
		
		//List employees
		ME.listEmployees();
		
		//update
		ME.update(emp1,7500);
	}
	private void update(Integer emp1, int salary) {
		Session session=factory.openSession();
		org.hibernate.Transaction tx;
		try {
			tx=session.beginTransaction();
			Employee employee=(Employee) session.get(Employee.class, emp1);
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
	}
	private void listEmployees() {
		Session session=factory.openSession();
		org.hibernate.Transaction tx;
		try {
			tx=session.beginTransaction();
			List employees=session.createQuery("FROM Employee").list();
			for(Iterator iterator1=employees.iterator();iterator1.hasNext();){
				Employee employee=(Employee) iterator1.next();
				System.out.println("First name: "+employee.getFirstName());
				System.out.println("Last Name: "+employee.getLastName());
				System.out.println("Salary: "+employee.getSalary());
				Set certificate=employee.getCertificates();
				for(Iterator iterator2=certificate.iterator();iterator2.hasNext();){
					Certificate certi=(Certificate) iterator2.next();
					System.out.println("Certifcate: "+certi.getName());
					
				}
			}
			tx.commit();
			
		} catch (HibernateException e) {
			e.printStackTrace();
		}finally
		{
			session.close();
		}
		
	}
	//add employee method
	private Integer addEmployee(String fName, String lName, int salary,
			HashSet set1) {
		Session session=factory.openSession();
		
		org.hibernate.Transaction tx=null;
		Integer employeeID=null;
		try {
			tx=session.beginTransaction();
			Employee employee =new Employee(fName,lName,salary);
			employee.setCertificates(set1);
			employeeID=(Integer) session.save(employee);
			tx.commit();
			
			
			
		} catch (HibernateException e) {
			e.printStackTrace();
		
		}
		finally{
			session.close();
		}
		return employeeID;
	}

}
