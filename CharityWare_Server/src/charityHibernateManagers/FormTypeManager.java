package charityHibernateManagers;

import sharedHibernateResources.ConnectionManager;
import charityHibernateEntities.FormType;

public class FormTypeManager {
	private ConnectionManager conn;
	public FormTypeManager(String DBConfname){
		//this.DBConfname = DBConfname;
		System.out.println("HELLLOOO!");
		conn = new ConnectionManager(DBConfname);
		//conn.setDBConfname(DBConfname);
	}
	
	public FormType getFormType(Integer formTypeId){
		FormType formType = (FormType)conn.get(FormType.class,formTypeId);
		return formType;
	}

}
