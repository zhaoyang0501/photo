package com.pzy.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {
	
	 @Bean
	 public FilterRegistrationBean siteMeshFilter() {
	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
	        filterRegistrationBean.setFilter( new  org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter());
	        filterRegistrationBean.setName("struts2");
	        filterRegistrationBean.addUrlPatterns("/*");
	        return filterRegistrationBean;
	 }
	 
	 
}
