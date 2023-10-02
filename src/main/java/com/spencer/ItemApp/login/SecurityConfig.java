package com.spencer.ItemApp.login;

import com.spencer.ItemApp.Users.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private static MvcRequestMatcher.Builder mvc = mvc(new HandlerMappingIntrospector());
	@Bean
	public static MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector){
		return new MvcRequestMatcher.Builder(introspector);
	}
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.csrf().disable()
		.authorizeRequests()
		.requestMatchers(mvc.pattern("/login**"), mvc.pattern("/WEB-INF/jsp/**")).permitAll()
		.and()
		.authorizeRequests()
		.requestMatchers(mvc.pattern("/item**"), mvc.pattern("/item/**")).authenticated()
		.and()
		.formLogin( form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/item")
				.loginProcessingUrl("/login")
				.failureUrl("/login?error=true")
				.permitAll()
				).logout(
						logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
						);
		return http.build();
	}
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager();
	}
	@Bean 
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
