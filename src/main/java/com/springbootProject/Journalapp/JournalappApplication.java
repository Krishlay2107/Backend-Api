package com.springbootProject.Journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalappApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext context=SpringApplication.run(JournalappApplication.class, args);
		 System.out.println(context.getEnvironment());

	}
	 @Bean
	 public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		 return new MongoTransactionManager(dbFactory);
	 }

}
