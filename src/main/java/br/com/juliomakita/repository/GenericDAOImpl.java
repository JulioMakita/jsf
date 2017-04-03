package br.com.juliomakita.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GenericDAOImpl<T> implements IGenericDAO<T> {
	
	protected static SessionFactory sessionFactory;
	
	static{
		
      try{
    	  if (sessionFactory == null){
    		  sessionFactory = new Configuration().configure().buildSessionFactory();
    	  }

       }catch (Throwable ex) { 
          System.err.println("Failed to create sessionFactory object." + ex);
          throw new ExceptionInInitializerError(ex); 
       }
	}
	
	/*public static Session getSession() throws HibernateException {         
		   Session sess = null;       
		   try {         
		       sess = sessionFactory.getCurrentSession();  
		   } catch (org.hibernate.HibernateException he) {  
		       sess = sessionFactory.openSession();     
		   }             
		   return sess;
		} 

	/*public GenericDAOImpl(Class<T> cl, SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
		
		if (sessionFactory == null)
			throw new RuntimeException("Session factory is null!!!");
	}*/

	@Override
	@SuppressWarnings("unchecked")
	public T get(Class<T> cl, Long id) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		T element = (T) session.get(cl, id);
		session.getTransaction().commit();
		return element;
	}

	@Override
	public T save(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(object);
		session.getTransaction().commit();
		return object;
	}

	@Override
	public void update(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
	}

	@Override
	public void delete(T object) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
	}

}