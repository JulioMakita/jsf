package br.com.juliomakita.repository;

import java.util.List;

import javax.inject.Named;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.juliomakita.model.Student;

@Named
public class StudentRepository extends GenericDAOImpl<Student>{
	
	
	public List<Student> findAll(){
		
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         list =  session.createCriteria(Student.class).list();
	         tx.commit();
	      }catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      
		return list;
	}
}
