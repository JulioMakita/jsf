package br.com.juliomakita.util;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.juliomakita.model.Student;

public class Teste {

	private static SessionFactory factory;
	 
	public static void main(String[] args) {
		
      try{
          factory = new Configuration().configure().buildSessionFactory();
       }catch (Throwable ex) { 
          System.err.println("Failed to create sessionFactory object." + ex);
          throw new ExceptionInInitializerError(ex); 
       }
		

      addStudent("teste", 11);
       
      listStudents();
	}
	
	
   public static void addStudent(String name, Integer age){
	   
	      Session session = factory.openSession();
	      Transaction tx = null;

	      try{
	         tx = session.beginTransaction();
	         
	         Student student = new Student();
	         student.setName(name);
	         student.setAge(age);
	         
	         session.save(student); 
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	}
   
   public static void listStudents( ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         List<Student> list =  session.createCriteria(Student.class).list();
         for (Student student : list){
            System.out.println("Name: " + student.getName()); 
            System.out.println("Age: " + student.getAge()); 
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
	}
   
  /* public void updateEmployee(Integer EmployeeID, int salary ){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                    (Employee)session.get(Employee.class, EmployeeID); 
         employee.setSalary( salary );
		 session.update(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }

   public void deleteEmployee(Integer EmployeeID){
      Session session = factory.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                   (Employee)session.get(Employee.class, EmployeeID); 
         session.delete(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }*/
}
