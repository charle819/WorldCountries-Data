package com.foobar.WorldData.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
  /*use the below code for dynamically setting envionment specific properties*/
//@PropertySource(value={"classpath:db.${env}.properties"})   
@PropertySource(value={"classpath:db.dev.properties"})
public class JpaConfigurations {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getDatasource()
	{
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public JpaVendorAdapter getJPAVendorAdapter()
	{
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		return jpaVendorAdapter;
	}
	
	public Properties getJpaProperties()
	{
		Properties p = new Properties();
		p.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		p.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		p.setProperty("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		p.setProperty("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		
		return p;
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory()
	{
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		factoryBean.setDataSource(getDatasource());
		factoryBean.setPackagesToScan(new String[] {"com.foobar.WorldData.model"});
		factoryBean.setJpaVendorAdapter(getJPAVendorAdapter());
		factoryBean.setJpaProperties(getJpaProperties());
		return factoryBean;
	}
	
	@Bean
	@Autowired
	public PlatformTransactionManager getTransactionManager(EntityManagerFactory emf)
	{
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;
	}
}
