package com.easybytes.config;


import com.easybytes.exceptionhandling.CustomAccessDeniedHandler;
import com.easybytes.exceptionhandling.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("!prod")
public class ProjectSecurityConfiguration {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
        http.requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // Accept only HTTPS request
                .csrf(csrfConfig -> csrfConfig.disable())
                .authorizeHttpRequests((requests) ->
                requests.requestMatchers("/myAccount","/myBalance","/myCards","/myLoans").authenticated());
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/contact","/notices","/error","/register").permitAll());

        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));
       // http.exceptionHandling( ehc -> ehc.authenticationEntryPoint(new CustomAuthenticationEntryPoint())); // Global Authentication Exception handler

        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }



}
