package com.daniele.myapp.web.config;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.daniele.myapp.db.config.H2DataSourceInitializer;

@ComponentScan({ "com.daniele" })
@EnableTransactionManagement
@Configuration
public class HibernateInitializer {
	
	@Inject
	H2DataSourceInitializer dataSource;
	
	@Inject
	LocalContainerEntityManagerFactoryBean entityManagerFactory;
	
	@Inject
	PlatformTransactionManager transactionManager;
	
}