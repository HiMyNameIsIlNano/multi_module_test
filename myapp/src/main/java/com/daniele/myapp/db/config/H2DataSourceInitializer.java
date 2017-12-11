package com.daniele.myapp.db.config;

import com.daniele.mybackend.shared.JOOQToSpringExceptionTransformer;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.flywaydb.core.Flyway;
import org.h2.tools.Server;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.daniele.mydatabase", entityManagerFactoryRef="entityManagerFactory")
public class H2DataSourceInitializer {

	@Inject
	private Environment env;

	@Bean(initMethod = "migrate")
	Flyway flyway() {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(true);
		flyway.setLocations("filesystem:../mydatabase/src/main/resources/migrations");
		flyway.setDataSource(dataSource());
		return flyway;
	}

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
	@DependsOn("flyway")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("com.daniele");
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

	@Bean
	public LazyConnectionDataSourceProxy lazyConnectionDataSource() {
		return new LazyConnectionDataSourceProxy(dataSource());
	}

	@Bean
	public TransactionAwareDataSourceProxy transactionAwareDataSource() {
		return new TransactionAwareDataSourceProxy(lazyConnectionDataSource());
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(lazyConnectionDataSource());
	}

	@Bean
	public DataSourceConnectionProvider connectionProvider() {
		return new DataSourceConnectionProvider(transactionAwareDataSource());
	}

	@Bean
	public JOOQToSpringExceptionTransformer jooqToSpringExceptionTransformer() {
		return new JOOQToSpringExceptionTransformer();
	}

	@Bean
	public DefaultConfiguration configuration() {
		DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
		jooqConfiguration.set(connectionProvider());
		jooqConfiguration.set(new DefaultExecuteListenerProvider(jooqToSpringExceptionTransformer()));
		jooqConfiguration.set(SQLDialect.H2);
		return jooqConfiguration;
	}

	@Bean
	public DefaultDSLContext dsl() {
		return new DefaultDSLContext(configuration());
	}

	private Properties additionalProperties() {
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