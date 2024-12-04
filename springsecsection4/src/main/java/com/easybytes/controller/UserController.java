package com.easybytes.controller;

import com.easybytes.model.Customer;
import com.easybytes.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer)
    {
        try {

            String hashpwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashpwd);
            customerRepository.save(customer);

            if(customer.getId() > 0)
                return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User is registered Successfully");
            else
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User Registration failed");
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred: "+ ex.getMessage());
        }
    }
}
