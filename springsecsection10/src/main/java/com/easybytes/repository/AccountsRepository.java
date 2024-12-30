package com.easybytes.repository;

import com.easybytes.model.Accounts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountsRepository  extends CrudRepository<Accounts, String> {

    Accounts findByCustomerId(long customerId);
}
