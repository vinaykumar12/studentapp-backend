package com.studentapp.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class DevelopmentConfiguration {
	
	@Bean(name="dataSource")
	public DriverManagerDataSource driverManagerDataSource(){
		DriverManagerDataSource driverManagerDataSource=new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/studentapp");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("123456");
		
		
		return driverManagerDataSource;
		
	}
	
	public LocalContainerEntityManagerFactoryBean managerFactoryBean(DriverManagerDataSource dataSource){
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setPackagesToScan(new String[]{"com.studentapp.model"});
		localContainerEntityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		Map<String,Object> jpaProperties = new HashMap<String,Object>();
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.use_sql_comments", "true");
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        
        localContainerEntityManagerFactoryBean.setJpaPropertyMap(jpaProperties);
		
		return localContainerEntityManagerFactoryBean;
	}

}
