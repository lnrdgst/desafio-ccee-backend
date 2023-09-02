package com.ccee.desafiocceebackend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

	@Override
	public void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()
	        .antMatchers("/api/**").permitAll()
	        .antMatchers("/auth").permitAll()
	        .and().cors()
	        .and().csrf().disable()
	        .httpBasic().disable(); // Desabilitar a autenticação básica
	}


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }
}



