package com.componente.factinven;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import com.componente.factinven.fileupload.StorageProperties;
import com.componente.factinven.fileupload.StorageService;


@SpringBootApplication()
@EnableConfigurationProperties({
	StorageProperties.class
})
public class FactinvenApplication {

	public static void main(String[] args) {
		SpringApplication.run(FactinvenApplication.class, args);
	}
	
	
// 	  @Configuration
// 	  //@Order(SecurityProperties.IGNORED_ORDER)
// 	  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
// 	    @Override
// 	    protected void configure(HttpSecurity http) throws Exception {
// 	    	 http.csrf().disable().authorizeRequests().anyRequest().permitAll();
// //	      http
// //	        .httpBasic()
// //	      .and()
// //	        .authorizeRequests()
// //	          .antMatchers("/index.html", "/", "/home", "/login").permitAll()
// //	          .anyRequest().authenticated();
// 	    }
// 	  }
	  
	  
		@Bean
		CommandLineRunner init(StorageService storageService) {
			return (args) -> {
				//storageService.deleteAll();
				storageService.init();
			};
		}
				
}
