package env.Entities;

import java.io.Serializable;

import android.content.Context;

import com.webviewprototype.facade.impl.DBManager;

public class DataBean {

	private DBManager manager;
	private Form form;
	private FilledForm fform;
	private Boolean created=false;
	public Boolean getCreated() {
		return created;
	}

	public void setCreated(Boolean created) {
		this.created = created;
	}

	private static final DataBean databean = new DataBean();
	
	
	public DataBean() {
		
	}

	public DBManager getManager() {
		return manager;
	}
	

	public void setManager(DBManager manager) {
		this.manager = manager;
	}
	
	public static DataBean getDataBean() {
		return databean;
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public FilledForm getFform() {
		return fform;
	}

	public void setFform(FilledForm fform) {
		this.fform = fform;
	}
	

	

}
