package com.easybytes.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="customer")
@Getter @Setter
public class Customer  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;

    private String email;
    private  String name;

    @Column(name = "mobile_number")
    private  String mobileNumber;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @Column(name = "create_dt")
    private Date createDt;

    private  String role;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    /*Hibernate @OneToMany Relationship Causes Infinite Loop Or Empty Entries in JSON Result*/
    @JsonManagedReference
    private Set<Authority> authorities;

}
