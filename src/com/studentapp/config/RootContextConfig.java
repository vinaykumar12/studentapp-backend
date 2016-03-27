package com.studentapp.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ComponentScan({"com.studentapp.services","com.studentapp.dao",
	"com.studentapp.init","com.studentapp.security"})
public class RootContextConfig {
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory,
			DriverManagerDataSource driverManagerDataSource){
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		transactionManager.setDataSource(driverManagerDataSource);
		
		return transactionManager;
	}
	
	

}
