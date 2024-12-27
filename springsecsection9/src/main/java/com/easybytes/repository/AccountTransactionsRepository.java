package com.easybytes.repository;

import com.easybytes.model.AccountTransactions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccountTransactionsRepository extends CrudRepository<AccountTransactions, String> {


    List<AccountTransactions> findByCustomerIdOrderByTransactionDtDesc(long customerId);
}
