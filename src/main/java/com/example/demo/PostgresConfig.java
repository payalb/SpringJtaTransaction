package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.postgresql.xa.PGXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.atomikos.icatch.jta.hibernate4.AtomikosPlatform;
import com.atomikos.jdbc.AtomikosDataSourceBean;

@Configuration
@EnableJpaRepositories(basePackages="com.example.demo.dao1",
entityManagerFactoryRef="entityManagerFactory")
public class PostgresConfig {

	@Autowired Environment env;
	@Primary
    @Bean
    public AtomikosDataSourceBean dataSource2() {
  
		AtomikosDataSourceBean ds= new AtomikosDataSourceBean();
		PGXADataSource db = new PGXADataSource();
        db.setUrl(env.getProperty("spring.datasource1.jdbc-url"));
        db.setUser(env.getProperty("spring.datasource1.username"));
        db.setPassword(env.getProperty("spring.datasource1.password"));
        ds.setXaDataSource(db);
        ds.setUniqueResourceName("ds2");
		return ds;
    }
 
	  @Primary
	  @Bean("entityManagerFactory")
	  public LocalContainerEntityManagerFactoryBean 
	  entityManagerFactory1(
	  ) {
		  LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
		  HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	        em.setJpaVendorAdapter(vendorAdapter);
		  Map<String,String> properties=  new HashMap<>();
		  properties.put("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
		  properties.put( "hibernate.show_sql","true");
		  properties.put(  "hibernate.hbm2ddl.auto","create");
		  properties.put("hibernate.transaction.factory_class",
	                "org.hibernate.transaction.JTATransactionFactory");
		  properties.put("hibernate.transaction.jta.platform",
				  AtomikosPlatform.class.getName());  // required by Hibernate 5
			properties.put("hibernate.transaction.coordinator_class", "jta");

		  properties.put("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect");
		  properties.put("hibernate.jdbc.lob.non_contextual_creation","true");
		  em.setJtaDataSource(dataSource2());
	      em.setPackagesToScan("com.example.demo.dto");
	     em.setJpaPropertyMap(properties);
	     em.setPersistenceUnitName("postgres1");
	    
		  em.afterPropertiesSet();
		  return em;
	  }
	    
	
}
