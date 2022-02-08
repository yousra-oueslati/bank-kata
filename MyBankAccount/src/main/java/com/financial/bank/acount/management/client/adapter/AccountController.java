package com.financial.bank.acount.management.client.adapter;

import java.math.BigDecimal;
import java.util.List;

import com.financial.bank.acount.management.domain.model.Statement;
import com.financial.bank.acount.management.domain.port.client.AccountStatement;
import org.springframework.web.bind.annotation.*;

import com.financial.bank.acount.management.domain.port.client.Deposit;
import com.financial.bank.acount.management.domain.port.client.Withdrawal;

@RestController
@RequestMapping("/account")
public class AccountController {

    // Deposit use case
    private final Deposit depositFeature;
    // Withdrawal use case
    private final Withdrawal withdrawalFeature;
    //StatementTransaction use case
    private final AccountStatement statementFeature;

    public AccountController(Deposit depositFeature, Withdrawal withdrawalFeature, AccountStatement statementFeature) {
        this.depositFeature = depositFeature;
        this.withdrawalFeature = withdrawalFeature;
        this.statementFeature = statementFeature;
    }

    @PostMapping(value = "/{accountNumber}/deposit/{depositAmount}")
    void deposit(@PathVariable String accountNumber, @PathVariable BigDecimal depositAmount) throws Exception {
        depositFeature.deposit(accountNumber, depositAmount);
    }

    @PostMapping(value = "/{accountNumber}/withdraw/{withdrawalAmount}")
    void withdraw(@PathVariable String accountNumber, @PathVariable BigDecimal withdrawalAmount) throws Exception {
        withdrawalFeature.withdraw(accountNumber, withdrawalAmount);
    }

    @GetMapping(value = "/{accountNumber}")
    List<Statement> loadAccountStatement(@PathVariable final String accountNumber) throws Exception {
        return  statementFeature.getAccountStatement(accountNumber);
    }
}
