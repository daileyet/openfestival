package com.openthinks.festival.test.service;

import sql.dhibernate.Session;
import sql.dhibernate.support.SessionFactory;
import sql.lang.Configurator;
import sql.lang.ConfiguratorFactory;

import com.openthinks.festival.resource.ResourceManagement;

public class FsDaoTest {

	static Configurator configuration = ConfiguratorFactory
			.getInstance(ResourceManagement.getDbResource());

	public Session getSession() {
		return SessionFactory.getSession(configuration);
	}

}
