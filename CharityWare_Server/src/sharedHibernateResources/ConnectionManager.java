package sharedHibernateResources;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

public class ConnectionManager {
	private  SessionFactory factory;
	//private String DBConfname;
	
	public ConnectionManager(String DBConfname){
		//this.DBConfname = DBConfname;
		Configuration conf = new Configuration();
		conf.configure(((DBConfname == null || DBConfname == "" ) ? "/systemHibernateEntities/hibernate.cfg.xml" : "/charityHibernateEntities/"+DBConfname));
		factory = conf.buildSessionFactory();
	}
	
	/*public String getDBConfname() {
		return DBConfname;
	}

	public void setDBConfname(String DBConfname) {
		this.DBConfname = DBConfname;
	}*/

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
	
	
	public void runProcedure (String procedure,Map<String,String> parameters_map){
		Session session = this.getSession();
		Iterator<Entry<String,String>> parameters_map_iter1 = parameters_map.entrySet().iterator();
		String parameters = "";
		while(parameters_map_iter1.hasNext()){			
			String parameterName = parameters_map_iter1.next().getKey();
			parameters = parameters+":"+parameterName;
			if(parameters_map_iter1.hasNext())
				parameters = parameters+",";
		}
		String callprocedure = "CALL "+procedure+"("+parameters+")";
		
		Query query = session.createSQLQuery(callprocedure);
		Iterator<Entry<String,String>> parameters_map_iter2 = parameters_map.entrySet().iterator();
		while(parameters_map_iter2.hasNext()){
			Entry<String,String> parameters_entry = parameters_map_iter2.next();
			String parameterName = parameters_entry.getKey();
			String parameterValue = parameters_entry.getValue();
			query.setParameter(parameterName, parameterValue);
		}
		query.executeUpdate();	
		closeSession(session);
		return;
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
	
	public Session getSession(){
		//System.out.println("factory null?");		
		
		Session result;
		try{			
			if (factory ==null){
				/*Configuration conf = new Configuration();
				conf.configure(((DBConfname == null || DBConfname == "" ) ? "/systemHibernateEntities/hibernate.cfg.xml" : "/charityHibernateEntities/"+DBConfname));
				factory = conf.buildSessionFactory();*/
				result = factory.openSession();
				System.out.println("First openSession invoked");
			}else{
				result = factory.getCurrentSession();
				System.out.println("getCurrentSession invoked");
			}
		}catch(org.hibernate.HibernateException e){
			
			result = factory.openSession();
			System.out.println("Second openSession invoked");
		}
		return result;
	}	
	public List<?> searchCriteria(Class arg0,String column,Object obj){
		Session session = getSession();
		List<?> results =  session.createCriteria(arg0)
				.add(Restrictions.eq(column, obj)).list();
		closeSession(session);
	    return results;
	}
	
	public List<?> searchCriteria (Class arg0,String column,List<?> objects ){
		Session session = getSession();
		List<?> results =  session.createCriteria(arg0)
				.add(Restrictions.in( column,objects)).list();
		closeSession(session);
	    return results;
	}
	
	public Object searchCriteria (Class arg0,Integer objectid ){
		Session session = getSession();
		Object result = session.createCriteria(arg0)
				.add(Restrictions.idEq( new Integer(objectid)))
				.uniqueResult();
		closeSession(session);
	    return result;
	}
	
	public void closeSession(Session session){
		//session.close();
	}

}
