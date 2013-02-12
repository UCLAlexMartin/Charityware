package charityHibernateManagers;

import java.util.List;

import sharedHibernateResources.ConnectionManager;

import charityHibernateEntities.FieldType;


public class FieldTypeManager {
	private ConnectionManager conn;
	public FieldTypeManager(String DBConfname){
		conn = new ConnectionManager(DBConfname);
	}
	public FieldType getFieldType(Integer id){
		FieldType fieldType = (FieldType)conn.get(FieldType.class,id);
		return fieldType;
	}
	
	public List<FieldType> retrieve(){
		List<FieldType> fieldTypes = (List<FieldType>) conn.getTable("FieldType");
		return fieldTypes;
	}

}
