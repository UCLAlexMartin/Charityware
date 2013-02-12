package charityHibernateEntities;

public class FormKey implements Comparable<FormKey>
{
	private Integer formID;
	private String formName;
	
	public FormKey(Integer formID,String formName)
	{
		this.formID=formID;
		this.formName=formName;
	}

	public Integer getFormID() { return formID;}
	public String getFormName() { return formName;}
	
	public int compareTo(FormKey fk){
		if(fk==this) return 0;
		int i= formID.compareTo(fk.formID);
		return i;
	}
	
	@Override
	public int hashCode(){
		return formID.hashCode()+31*formName.hashCode();
	}
	
	@Override
	public boolean equals(Object o){
		if(o==this) return true;
		if(o==null || !(o instanceof FormKey)) return false;
		FormKey fk= FormKey.class.cast(o);
		return formID.equals(fk.formID) && formName.equals(fk.formName);
	}
	
	@Override
    public String toString(){
		return "("+formID+";"+formName+")";
    }
}

