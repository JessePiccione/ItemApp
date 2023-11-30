package com.spencer.ItemApp.config;

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

import com.spencer.ItemApp.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final MvcRequestMatcher.Builder mvc = mvc(new HandlerMappingIntrospector());

    @Bean
    public static MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(mvc.pattern("/login/**"),
                                mvc.pattern("/login**"),
                                mvc.pattern("/WEB-INF/jsp/**"),
                                mvc.pattern("/css/**"),
                                mvc.pattern("/js/**"),
                                mvc.pattern("/img/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home")
                        .loginProcessingUrl("/login")
                        .failureUrl("/login?error=true")
                        .permitAll()
                ).logout(
                        logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                );
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
