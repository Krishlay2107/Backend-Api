package com.springbootProject.Journalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableTransactionManagement
public class JournalappApplication {

	public static void main(String[] args) {
//		SpringApplication.run(JournalappApplication.class, args);
		SpringApplication.run(JournalappApplication.class,args);

	}

	 @Bean
	 public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
		 return new MongoTransactionManager(dbFactory);
	 }
	 @Bean
	  public RestTemplate m1()
	  {
		  return new RestTemplate();
	  }

}
// ec2-user@ec2-13-53-197-111.eu-north-1.compute.amazonaws.com
