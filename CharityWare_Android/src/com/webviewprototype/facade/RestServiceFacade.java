package com.webviewprototype.facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import env.Entities.Form;

public interface RestServiceFacade {
	//public ArrayList<Form> getData(String username);
	public Map<Integer,Map<Integer,List<String>>> getFormEntities(String username);
	
}
