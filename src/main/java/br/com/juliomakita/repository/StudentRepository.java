package br.com.juliomakita.repository;

import java.util.List;

import javax.inject.Named;
import javax.persistence.Query;

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
	         
	         Query query = session.createQuery("from Student");
	         list = query.getResultList();
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
