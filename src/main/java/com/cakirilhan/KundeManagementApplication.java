package com.cakirilhan;

import java.util.Date;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.cakirilhan.audit.AuditorAwareImpl;
import com.cakirilhan.audit.SpringSecurityAuditorAware;
import com.cakirilhan.domain.user.User;



@SpringBootApplication

public class KundeManagementApplication extends SpringBootServletInitializer {
	
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(KundeManagementApplication.class);
	    }

	public static void main(String[] args) {
		SpringApplication.run(KundeManagementApplication.class, args);
		
	}
@PostConstruct
public void init(){
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));  
    System.out.println("Spring boot application running in UTC timezone :"+new Date());   
}
}

