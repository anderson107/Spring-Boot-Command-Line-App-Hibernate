package com.alpha.AppHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppHibernateApplication {
	
	public AppHibernateApplication() {
		new Application().displayMenu();
	}

	public static void main(String[] args) {
		SpringApplication.run(AppHibernateApplication.class, args);
	}

}
