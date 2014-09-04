/**
 * 
 */
package com.openthinks.festival.data.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author minjdai
 *
 */
public abstract class AbstractFsJson implements Jsonable {
	
	private transient Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	/* (non-Javadoc)
	 * @see com.openthinks.festival.data.model.Jsonable#stringify()
	 */
	@Override
	public String stringify(){
		return gson.toJson(this);
	}

}
