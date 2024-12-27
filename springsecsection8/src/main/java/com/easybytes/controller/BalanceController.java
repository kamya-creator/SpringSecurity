package com.easybytes.controller;

import com.easybytes.model.AccountTransactions;
import com.easybytes.repository.AccountTransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {


    private final AccountTransactionsRepository accountTransactionsRepository;
    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id)
    {
        return accountTransactionsRepository.findByCustomerIdOrderByTransactionDtDesc(
             id
        );
    }
}
