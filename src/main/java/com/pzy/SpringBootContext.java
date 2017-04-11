package com.pzy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@ImportResource( {"classpath:struts.xml"} ) 
@ComponentScan(basePackages={"com.pzy.**"})
@SpringBootApplication
@EnableAutoConfiguration
@EnableMongoRepositories({"com.**.repository"})
@EntityScan("com.**.entity")

public class SpringBootContext extends SpringBootServletInitializer {

	 @Bean
	 public FilterRegistrationBean siteMeshFilter() {
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	        filterRegistrationBean.setFilter( new  org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter());
	        filterRegistrationBean.setName("struts2");
	        filterRegistrationBean.addUrlPatterns("/*");
	        return filterRegistrationBean;
	 }
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootContext.class);
	}
	
	public static void main(String args[]){
		SpringApplication.run(SpringBootContext.class, args);
		
	}
	
	
	
}
