package com.easybytes.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setHeader("eazybank-error-reason", "Authentication Failed" );

        // Custom message won't work if you don't remove below response.setError(..) line
        //response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.setContentType("application/json;charset=UTF-8");

        LocalDateTime currentTimeStamp = LocalDateTime.now();

        // Exception message is set in loadByUserName() func , go and check EazyBankUserDetailsService.java file
        String message = (authException != null && authException.getMessage() != null)? authException.getMessage() : "Unauthorized";
        String path =request.getRequestURI();

        String jsonResponse=String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                currentTimeStamp, HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                message, path);
        response.getWriter().write(jsonResponse);
    }
}
