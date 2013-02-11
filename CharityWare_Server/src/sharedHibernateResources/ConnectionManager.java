package sharedHibernateResources;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ConnectionManager {
	private  SessionFactory factory;
	private String DBConfname;
	
	public String getDBConfname() {
		return DBConfname;
	}

	public void setDBConfname(String DBConfname) {
		this.DBConfname = DBConfname;
	}

	//Stub to remove old function
	public  List<?> getTable(String table){
		return runSelectQuery("from "+table);
	}
	
	public  List<?> runSelectQuery(String querystr){
		Session session = this.getSession();
		Query query  = session.createQuery(querystr);
		List<?> result = query.list();
		closeSession(session);
		return result;
	}
	
	public  Object get(Class arg0,Serializable serial){
		Session session = this.getSession();
		Object result = session.get(arg0, serial);
		this.closeSession(session);
	    return result;
	}
	
	public  Serializable transaction(String method,Object obj){
		Session session = this.getSession();
		Transaction tx = null;
		Serializable serial = null;
		try{
	    	tx = session.beginTransaction();
	    	serial = (Serializable)Session.class.getMethod(method,Object.class).invoke(session,obj);
	    	tx.commit();
	    }catch(HibernateException hx) {
	    	if (tx!=null) {
	    		tx.rollback();
	    	}
	    	hx.printStackTrace();
	    }catch(IllegalAccessException e){
	    	e.printStackTrace();
	    }catch(IllegalArgumentException e){
	    	e.printStackTrace();
	    }catch(InvocationTargetException e){
	    	e.printStackTrace();
	    }catch(NoSuchMethodException e){
	    	e.printStackTrace();
	    }catch(SecurityException e){
	    	e.printStackTrace();
	    }finally{
	    	this.closeSession(session);
	    }
		return serial;
	}
	
	private Session getSession(){
		System.out.println("factory null?");
		if (factory ==null){
			Configuration conf = new Configuration();
			conf.configure(((DBConfname == null || DBConfname == "" ) ? "/systemHibernateEntities/hibernate.cfg.xml" : "/charityHibernateEntities/"+DBConfname));
			factory = conf.buildSessionFactory();
			return factory.openSession();
		}
		Session result;
		try{
			result = factory.getCurrentSession();
		}catch(org.hibernate.HibernateException e){
			result = factory.openSession();
		}
		return result;
	}
	
	
	
	private void closeSession(Session session){
		session.close();
	}

}
