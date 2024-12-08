package com.easybytes.events;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvents {

    // @EventListener annotation is mandatory to work the following code
    // by default spring security create these events of success login and failure , in our code we have to listen them and perform business logic
    // No extra configuration is required  in config package files to listen these events
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent)
    {
        log.info("Login Successfully for user {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent)
    {
        log.error("Login failed for user {} due to: {} ",
                failureEvent.getAuthentication().getName() ,
                failureEvent.getException().getMessage());
    }
}
