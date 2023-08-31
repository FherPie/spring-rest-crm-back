package com.componente.factinven;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

import com.componente.factinven.fileupload.StorageProperties;
import com.componente.factinven.fileupload.StorageService;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;


@SpringBootApplication()
@RestController
@EnableConfigurationProperties({
	StorageProperties.class
})
public class FactinvenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactinvenApplication.class, args);
	}
	
	
	  @Configuration
	  //@Order(SecurityProperties.IGNORED_ORDER)
	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	 http.csrf().disable().authorizeRequests().anyRequest().permitAll();
//	      http
//	        .httpBasic()
//	      .and()
//	        .authorizeRequests()
//	          .antMatchers("/index.html", "/", "/home", "/login").permitAll()
//	          .anyRequest().authenticated();
	    }
	  }
	  
	  
		@Bean
		CommandLineRunner init(StorageService storageService) {
			return (args) -> {
				//storageService.deleteAll();
				storageService.init();
			};
		}
				
}
