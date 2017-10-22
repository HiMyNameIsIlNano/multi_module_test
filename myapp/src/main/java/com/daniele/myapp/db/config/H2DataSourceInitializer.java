package com.daniele.myapp.db.config;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Properties;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class H2DataSourceInitializer {

	@Inject
	private Environment env;

	// jdbc:h2:mem:testdb
	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
				
		try {
			comboPooledDataSource.setDriverClass(env.getProperty("application.driver.class"));
			comboPooledDataSource.setJdbcUrl(env.getProperty("application.db.url"));
			comboPooledDataSource.setUser(env.getProperty("application.db.username"));
			comboPooledDataSource.setPassword(env.getProperty("application.db.password"));

			Integer minPoolSize = env.getProperty("application.db.minPoolSize", Integer.class);
			Integer acquireIncrement = env.getProperty("application.db.acquireIncrement", Integer.class);
			Integer maxPoolSize = env.getProperty("application.db.maxPoolSize", Integer.class);
			Integer maxStatements = env.getProperty("application.db.maxStatements", Integer.class);

			// the settings below are optional -- c3p0 can work with defaults
			comboPooledDataSource.setMinPoolSize(minPoolSize);
			comboPooledDataSource.setAcquireIncrement(acquireIncrement);
			comboPooledDataSource.setMaxPoolSize(maxPoolSize);
			comboPooledDataSource.setMaxStatements(maxStatements);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comboPooledDataSource;
	}

	// Start WebServer, access http://localhost:8082
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server startDBManager() throws SQLException {
		return Server.createWebServer();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.daniele" });
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();

		String showSql = env.getProperty("application.db.showSql");
		String hibernateDialect = env.getProperty("application.db.hibernateDialect");
		String hibernateHbm2ddlAuto = env.getProperty("application.db.hibernateHbm2ddlAuto");

		properties.setProperty("hibernate.show_sql", showSql);
		properties.setProperty("hibernate.dialect", hibernateDialect);
		properties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		return properties;
	}

}