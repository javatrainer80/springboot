package com.tech2java.eis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
  //@SpringBootConfiguration
  //@EnableAutoConfiguration
  //@ComponentScan

@EnableJpaRepositories("com.tech2java.eis.repository")
@EntityScan("com.tech2java.eis.entity")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EISApplication {

	public static void main(String[] args) {
		SpringApplication.run(EISApplication.class, args);
	}

}
