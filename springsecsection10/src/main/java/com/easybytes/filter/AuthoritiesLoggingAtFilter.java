package com.easybytes.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
@Slf4j
public class AuthoritiesLoggingAtFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(null != authentication)
        {
            log.info("User with name " + authentication.getName()  + " is being authenticated");
        }
        chain.doFilter(request, response);
    }
}
