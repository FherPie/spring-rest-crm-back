package com.componente.config.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter   {

    
	 @Override
	 protected void configure(HttpSecurity httpSecurity) throws Exception {
		 
		 httpSecurity
		 .csrf()
		 .disable().authorizeRequests().antMatchers("api/registration")
		 .permitAll()
		 .anyRequest()
		 .authenticated()
		 .and()
		 .httpBasic();
		 
	 };
	
	
	
   @Bean
   public PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
    
}