package com.easybytes.exceptionhandling;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        LocalDateTime currentTimeStamp = LocalDateTime.now();

        // Exception message is set in loadByUserName() func , go and check EazyBankUserDetailsService.java file
        String message = (accessDeniedException != null && accessDeniedException.getMessage() != null) ?
                accessDeniedException.getMessage() : "FORBIDDEN";

        response.setHeader("eazybank-denied-reason", "Authentication Failed");

        // Custom message won't work if you don't remove below response.setError(..) line
        //response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());

        response.setStatus(HttpStatus.FORBIDDEN.value());

        response.setContentType("application/json;charset=UTF-8");

        String path = request.getRequestURI();

        String jsonResponse = String.format("{\"timestamp\": \"%s\", \"status\": %d, \"error\": \"%s\", \"message\": \"%s\", \"path\": \"%s\"}",
                currentTimeStamp, HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(),
                message, path);
        response.getWriter().write(jsonResponse);
    }
}
