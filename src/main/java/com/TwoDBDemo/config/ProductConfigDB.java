package com.TwoDBDemo.config;

import java.util.Properties; 
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.SpringSessionContext;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.TwoDBDemo.constant.AppConstants;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "spring.datasource.prod")   
@EnableJpaRepositories(
	    basePackages = "com.TwoDBDemo",   
	    entityManagerFactoryRef = "prodEntityManager",
	    transactionManagerRef = "prodTranxManager" 
	)
@AutoConfigureAfter 
public class ProductConfigDB {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource prodDatasource() {

		DriverManagerDataSource dmdatasource = new DriverManagerDataSource();
		dmdatasource.setDriverClassName(environment.getRequiredProperty("spring.datasource.prod.proddb.driverClassName"));
		dmdatasource.setUrl(environment.getRequiredProperty("spring.datasource.prod.proddb.url"));
		dmdatasource.setUsername(environment.getRequiredProperty("spring.datasource.prod.proddb.username"));
		dmdatasource.setPassword(environment.getRequiredProperty("spring.datasource.prod.proddb.password"));
		return dmdatasource;
	}
  
	@Bean
	protected Properties prodHibenateConfig() {
		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty(AppConstants.HIBERNATE_DIALECT,
				environment.getRequiredProperty(AppConstants.HIBERNATE_DIALECT));
		hibernateProperties.setProperty(AppConstants.HIBERNATE_SHOW_SQL,
				environment.getRequiredProperty(AppConstants.HIBERNATE_SHOW_SQL));
		hibernateProperties.setProperty(AppConstants.HIBERNATE_USE_SQL_COMMENTS,
				environment.getProperty(AppConstants.HIBERNATE_USE_SQL_COMMENTS));
		hibernateProperties.setProperty(AppConstants.HIBERNATE_FORMAT_SQL,
				environment.getProperty(AppConstants.HIBERNATE_FORMAT_SQL));
		hibernateProperties.setProperty(AppConstants.HIBERNATE_GENERATE_STATISTICS,
				environment.getProperty(AppConstants.HIBERNATE_GENERATE_STATISTICS));
		hibernateProperties.setProperty(AppConstants.JAVAX_PERSISTENCE_VALIDATION_MODE,
				environment.getProperty(AppConstants.JAVAX_PERSISTENCE_VALIDATION_MODE));
		hibernateProperties.setProperty(AppConstants.ORG_HIBERNATE_ENVERS_STORE_DATA_AT_DELETE,
				environment.getProperty(AppConstants.ORG_HIBERNATE_ENVERS_STORE_DATA_AT_DELETE));
		hibernateProperties.setProperty(AppConstants.ORG_HIBERNATE_ENVERS_GLOBAL_WITH_MODIFIED_FLAG,
				environment.getProperty(AppConstants.ORG_HIBERNATE_ENVERS_GLOBAL_WITH_MODIFIED_FLAG));
		hibernateProperties.setProperty(AppConstants.HIBERNATE_HBM2DDL_AUTO,
				environment.getProperty(AppConstants.HIBERNATE_HBM2DDL_AUTO));
		hibernateProperties.setProperty(AppConstants.HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS,
				SpringSessionContext.class.getName());

		return hibernateProperties;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean prodEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(prodDatasource());
		entityManagerFactoryBean.setJpaProperties(prodHibenateConfig());

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);

		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		entityManagerFactoryBean.setPackagesToScan("com.TwoDBDemo.model.prod");
		return entityManagerFactoryBean;
	}
	
	@Primary
    @Bean
    public PlatformTransactionManager prodTranxManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
        		prodEntityManager().getObject());
        return transactionManager;
    }
  
}
