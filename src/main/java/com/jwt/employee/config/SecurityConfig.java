package com.jwt.employee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.employee.security.JwtAuthenticationEntryPoint;
import com.jwt.employee.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
               .cors(cors -> cors.disable())
                .authorizeHttpRequests(
                		auth->
                		            auth.requestMatchers("/home**").authenticated() 
                		              .requestMatchers("/auth/login").permitAll()
                		        .requestMatchers("/home/create-user").permitAll()
                		              .anyRequest().authenticated())
                                  .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                                  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                                  
                                  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
                                  return http.build();        
                  
    }
    
    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider()
    {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider .setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	return provider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
   
}
        
        
        
        
        /*
                requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll()
                .anyRequest()
                .authenticated()
                .and().exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    */


