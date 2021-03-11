package com.alpha.Config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alpha.dao.*;
import com.alpha.entities.card.AmericanExpress;
import com.alpha.entities.card.Card;
import com.alpha.entities.card.Mastercard;
import com.alpha.entities.card.Visa;
import com.alpha.entities.customer.Customer;

@Configuration
@ComponentScan(basePackages = "com.alpha.dao")
@EnableTransactionManagement
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DbConfig {

	@Autowired
	private DbConnection dataSource;

	@Bean
	public DataSource dataSource() {
		return dataSource.getConnection();
	}

	@Lazy
	@Bean
	public CustomerDao customerDao() throws IOException {
		CustomerDao customer = new CustomerDaoImpl();
		customer.setSessionFactory(sessionFactory());
		return customer;
	}

	@Lazy
	@Bean
	public CardDao cardDao() throws IOException {
		CardDao card = new CardDaoImpl();
		card.setSessionFactory(sessionFactory());
		return card;
	}
	
	private Properties hibernateProperties() {
		Properties hibernateProp = new Properties();
		hibernateProp.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		hibernateProp.put("hibernate.format_sql", true);
		hibernateProp.put("hibernate.use_sql_comments", true);
		hibernateProp.put("hibernate.show_sql", true);
		hibernateProp.put("hibernate.max_fetch_depth", 3);
		hibernateProp.put("hibernate.jdbc.batch_size", 10);
		hibernateProp.put("hibernate.jdbc.fetch_size", 50);
		
		return hibernateProp;
	}

	@Bean
	public SessionFactory sessionFactory() throws IOException {
		LocalSessionFactoryBean session = new LocalSessionFactoryBean();
		session.setDataSource(dataSource());
		session.setPackagesToScan("com.alpha.entities");
		session.setHibernateProperties(hibernateProperties());
		session.afterPropertiesSet();
		session.setAnnotatedClasses(Customer.class);
		session.setAnnotatedClasses(Card.class);
		session.setAnnotatedClasses(AmericanExpress.class);
		session.setAnnotatedClasses(Mastercard.class);
		session.setAnnotatedClasses(Visa.class);
		return session.getObject();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() throws IOException {
		return new HibernateTransactionManager(sessionFactory());
	}
}
