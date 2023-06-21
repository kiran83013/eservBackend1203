package com.travel.travtronics.config;

import java.util.HashMap;
import java.util.Objects;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = "com.travel.travtronics.eserv", entityManagerFactoryRef = "eservEntityManager", transactionManagerRef = "eservTransactionManager")
public class PersistenceEservConfiguration {

	@Autowired
	private Environment env;

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean eservEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(eservDataSource());
		em.setPackagesToScan("com.travel.travtronics.eserv");
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Primary
	@Bean
	public DataSource eservDataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("eserv.datasource.driver-class-name")));
		dataSource.setUrl(env.getProperty("eserv.datasource.url"));
		dataSource.setUsername(env.getProperty("eserv.datasource.username"));
		dataSource.setPassword(env.getProperty("eserv.datasource.password"));

		return dataSource;
	}

	@Primary
	@Bean
	public PlatformTransactionManager eservTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(eservEntityManager().getObject());
		return transactionManager;
	}
}
