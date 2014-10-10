package com.openthinks.festival.web;

import sql.dhibernate.support.SessionFactory;
import sql.lang.Configurator;
import sql.lang.ConfiguratorFactory;

import com.openthinks.easyweb.annotation.configure.EasyConfigure;
import com.openthinks.easyweb.annotation.configure.RequestSuffixs;
import com.openthinks.easyweb.annotation.configure.ScanPackages;
import com.openthinks.easyweb.context.Bootstrap;
import com.openthinks.festival.resource.ResourceManagement;

/**
 * 
 * @author minjdai
 *
 */
@EasyConfigure
@ScanPackages({"com.openthinks.festival.web.controller"})
@RequestSuffixs("")
public class OpenFestivalWebConfigure implements Bootstrap {

	@Override
	public void initial() {
		initialSessionFactory();
		//TODO initial other configuration
	}

	private void initialSessionFactory() {
		Configurator configuration = ConfiguratorFactory
				.getInstance(ResourceManagement.getDbResource());
		SessionFactory.setDefaultConfigurator(configuration);
	}

}
