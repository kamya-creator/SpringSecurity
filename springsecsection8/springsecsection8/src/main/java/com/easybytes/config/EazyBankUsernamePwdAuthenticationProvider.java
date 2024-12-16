package com.easybytes.config;

import com.easybytes.repository.CustomerRepository;
import lombok.Locked;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("!prod")
public class EazyBankUsernamePwdAuthenticationProvider  implements AuthenticationProvider {

    private  final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // user entered details
        String username = authentication.getName();
        String pwd = (String) authentication.getCredentials();

        // DB loaded details
        UserDetails user = userDetailsService.loadUserByUsername(username);
        String savedPwd = user.getPassword();

        // Password encoder matches() func takes user entered password and db stored password, it Internally "HASH"
        // user entered password and matches with db loaded password
        //------------------------------------------
        // No PASSWORD MATCHING for NON_PROD Env
        //----------------------------------------
        /*if(passwordEncoder.matches(pwd, savedPwd))
        {
            return  new UsernamePasswordAuthenticationToken(username, pwd, user.getAuthorities());
        }
        else {
            throw new BadCredentialsException("Invalid Password");
        }

         */

        return  new UsernamePasswordAuthenticationToken(username, pwd, user.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
