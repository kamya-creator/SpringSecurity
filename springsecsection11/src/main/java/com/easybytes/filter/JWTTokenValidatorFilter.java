package com.easybytes.filter;

import com.easybytes.constants.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // received Jwt token from request Header
        String jwt = request.getHeader(ApplicationConstants.JWT_HEADER);
        if( null != jwt)
        {
            try{
                Environment env = getEnvironment();

                // Getting secreteKey from Application server
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

                // creating signature for verification from secrete [Application Server Secret]
                SecretKey secreteKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                // Comparing both JWT TOKENS to authenticate User
                Claims claims = Jwts.parser().verifyWith(secreteKey).build().parseSignedClaims(jwt).getPayload();


                String username = (String) claims.get("username");
                String authorities = (String) claims.get("authorities");

                // If user is Authenticated then , new Authentication object is created and saved into SecurityContextHolder
                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            catch (Exception exception)
            {
                throw new BadCredentialsException("Invalid Credentials Received");
            }
        }
        filterChain.doFilter(request, response);

    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user");
    }
}
