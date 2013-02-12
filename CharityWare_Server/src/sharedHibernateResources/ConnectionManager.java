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
	private  SessionFactory charityFactory;
	private  SessionFactory systemFactory;
	//private String DBConfname;
	
	public ConnectionManager(String DBConfname){
		//this.DBConfname = DBConfname;
		Configuration conf = new Configuration();
		
		//conf.configure(((DBConfname == null || DBConfname == "" ) ? "/systemHibernateEntities/hibernate.cfg.xml" : "/charityHibernateEntities/"+DBConfname));
		if((DBConfname!=null)&&(DBConfname != ""))
		{
			conf = new Configuration();
			conf.configure("/charityHibernateEntities/"+DBConfname);
			charityFactory = conf.buildSessionFactory();
		}
		conf = new Configuration();
		conf.configure("/systemHibernateEntities/hibernate.cfg.xml");
		systemFactory = conf.buildSessionFactory();
	}
	
	/*public String getDBConfname() {
		return DBConfname;
	}

	public void setDBConfname(String DBConfname) {
		this.DBConfname = DBConfname;
	}*/

	//Stub to remove old function
	public  List<?> getTable(String table,Integer type){
		return runSelectQuery("from "+table,type);
	}
	
	public  List<?> runSelectQuery(String querystr,Integer type){
		Session session = this.getSession(type);
		Query query  = session.createQuery(querystr);
		List<?> result = query.list();
		closeSession(session);
		return result;
	}
	
	public  Object get(Class arg0,Serializable serial,Integer type){
		Session session = this.getSession(type);
		Object result = session.get(arg0, serial);
		this.closeSession(session);
	    return result;
	}
	
	public  Serializable transaction(String method,Object obj,Integer type){
		Session session = this.getSession(type);
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
	
	private Session getSession(Integer type){
		//System.out.println("factory null?");		
		
		Session result;
		SessionFactory factory;
		if(type==0)
			factory = charityFactory;
		else
			factory = systemFactory;
		try{
			
			/*if (factory ==null){
				Configuration conf = new Configuration();
				conf.configure(((DBConfname == null || DBConfname == "" ) ? "/systemHibernateEntities/hibernate.cfg.xml" : "/charityHibernateEntities/"+DBConfname));
				factory = conf.buildSessionFactory();
				result = factory.openSession();
				System.out.println("First openSession invoked");
			}else{*/
				result = factory.getCurrentSession();
				System.out.println("getCurrentSession invoked");
			/*}*/
		}catch(org.hibernate.HibernateException e){
			
			result = factory.openSession();
			System.out.println("Second openSession invoked");
		}
		return result;
	}	
	
	private void closeSession(Session session){
		//session.close();
	}

}
