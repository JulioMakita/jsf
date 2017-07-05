package br.com.juliomakita.repository;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.juliomakita.model.Student;
import br.com.juliomakita.model.User;

public class StudentRepository extends GenericDAOImpl<Student>{
	
	
	public List<Student> findAllByUser(final User user){
		
		Session session = sessionFactory.openSession();
		List<Student> list = null;
		
	      Transaction tx = null;
	      try{
	         tx = session.beginTransaction();
	         
	         Query query = session.createQuery("from Student student where student.user.id=:userId ");
	         query.setParameter("userId", user.getId());
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
