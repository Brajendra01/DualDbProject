package com.TwoDBDemo.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
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
import org.springframework.web.client.RestTemplate;
import com.TwoDBDemo.constant.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "spring.datasource.user")    
@EnableJpaRepositories(
	    basePackages = "com.TwoDBDemo.model.user",  
	    entityManagerFactoryRef = "userEntityManager", 
	    transactionManagerRef = "userTranxManager"
	)
@AutoConfigureBefore
public class UserConfigDB { 

	@Autowired
	private Environment environment;

	@Bean
	@Primary
	public DataSource userDatasource() {  

		DriverManagerDataSource dmdatasource = new DriverManagerDataSource();
		dmdatasource.setDriverClassName(environment.getRequiredProperty("spring.datasource.user.userdb.driverClassName"));
		dmdatasource.setUrl(environment.getRequiredProperty("spring.datasource.user.userdb.url"));
		dmdatasource.setUsername(environment.getRequiredProperty("spring.datasource.user.userdb.username"));
		dmdatasource.setPassword(environment.getRequiredProperty("spring.datasource.user.userdb.password"));
		return dmdatasource; 
	}
	 
	@Bean
	@Primary
	protected Properties userHibenateConfig() {
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
	@Primary
	public LocalContainerEntityManagerFactoryBean userEntityManager() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(userDatasource());
		entityManagerFactoryBean.setJpaProperties(userHibenateConfig());

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);

		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);

		entityManagerFactoryBean.setPackagesToScan("com.TwoDBDemo.model.user");
		return entityManagerFactoryBean;
	}    
	
	@Primary
    @Bean
    public PlatformTransactionManager userTranxManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          userEntityManager().getObject());
        return transactionManager;
    }
}
