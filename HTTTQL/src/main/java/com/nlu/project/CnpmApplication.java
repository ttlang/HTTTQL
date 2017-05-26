package com.nlu.project;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan({"com","com.nlu.dao"})
@EnableAutoConfiguration  // ham main dinh nghia lop cau hinh
@EnableJpaRepositories("com.nlu.repository")
@EntityScan("com.nlu.dao.entity")

public class CnpmApplication {
	@Bean
	public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
		return hemf.getSessionFactory();
		
	}
	public static void main(String[] args) {
		SpringApplication.run(CnpmApplication.class, args);
	}
}
