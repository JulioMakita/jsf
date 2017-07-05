package br.com.juliomakita.repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.juliomakita.model.User;

public class UserRepository extends GenericDAOImpl<User>{

	public User findByNameAndPassword(final User user){
		
		Session session = sessionFactory.openSession();
		
	      Transaction tx = null;
	      User userResult = null;
	      
	      try{
	         tx = session.beginTransaction();
	         
	         Query query = session.createQuery("from User user where user.name=:name and user.password=:password");
	         query.setParameter("name", user.getName());
	         query.setParameter("password", user.getPassword());
	         
	         userResult = (User)query.getSingleResult();

	         tx.commit();
	      }catch (NoResultException | HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         session.close(); 
	      }
	      
	      return userResult;
	}
}
