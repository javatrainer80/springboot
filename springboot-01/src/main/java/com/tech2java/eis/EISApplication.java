package com.tech2java.eis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
  //@SpringBootConfiguration
  //@EnableAutoConfiguration
  //@ComponentScan
public class EISApplication {

	public static void main(String[] args) {
		SpringApplication.run(EISApplication.class, args);
	}

}
