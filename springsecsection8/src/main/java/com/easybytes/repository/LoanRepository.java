package com.easybytes.repository;

import com.easybytes.model.Cards;
import com.easybytes.model.Loans;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository  extends CrudRepository<Loans, Long> {

    List<Loans> findByCustomerIdOrderByStartDtDesc(long customerId);
}
