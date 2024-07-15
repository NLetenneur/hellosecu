package fr.diginamic.hello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class SecurityConfig {

//	@Bean
//	public UserDetailsService userDetailService() {
//		UserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		UserDetails user= User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
//		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password").roles("ADMIN").build();
//		userDetailsManager.createUser(user);			
//		userDetailsManager.createUser(admin);
//		return userDetailsManager;
//
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();

	}
}
