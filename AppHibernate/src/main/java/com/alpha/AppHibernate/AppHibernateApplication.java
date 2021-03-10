package com.alpha.AppHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class AppHibernateApplication {
	
	public AppHibernateApplication() {
		new Application().displayMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppHibernateApplication.class, args);
	}

}
