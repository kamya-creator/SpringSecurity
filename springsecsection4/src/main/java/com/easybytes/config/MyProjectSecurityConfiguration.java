package com.easybytes.config;


import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class MyProjectSecurityConfiguration {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated());
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/contact","/notices","/error").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
       // UserDetails user =  User.withUsername("user").password("{noop}12345").authorities("read").build();
       // UserDetails admin =  User.withUsername("admin").password("{noop}54321").authorities("admin").build();
        UserDetails user =  User.withUsername("user").password("{bcrypt}$2a$12$2A5RCK0dM6/r5dK8CfhJAe.cHtVy6EAROs9XlTdPl0/Dpor4Ex7VW").authorities("read").build();
        UserDetails admin =  User.withUsername("admin").password("{bcrypt}$2a$12$gUbQwcw.uuDoSe76yHom0eQiPMDQJAxd/na9BgDA4WLglaQ0IJRWG").authorities("admin").build();

        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
