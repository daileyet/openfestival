/**
 * 
 */
package com.openthinks.festival.resource;

import java.io.InputStream;

/**
 * @author minjdai
 * 
 */
public class ResourceManagement {

	public static InputStream getResource(String resourceName) {
		return ResourceManagement.class.getResourceAsStream(resourceName);
	}

	public static InputStream getDbResource() {
		return getResource("dbconfig.properties");
	}

	public static InputStream getLogResource() {
		return getResource("log4j.properties");
	}

}
