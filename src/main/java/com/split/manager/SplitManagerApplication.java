package com.split.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages="com.split.manager")
public class SplitManagerApplication extends SpringBootServletInitializer {

	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	  return application.sources(SplitManagerApplication.class);
	 }
	
	public static void main(String[] args) {
		SpringApplication.run(SplitManagerApplication.class, args);
	}

}
