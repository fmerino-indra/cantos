package org.fmm.cantos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TestConfig {
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}	

//	@Bean
//	public ServletWebServerApplicationContext webServerApplicationContext() {
//	    return new AnnotationConfigServletWebApplicationContext(); 
//	}	
	
}
