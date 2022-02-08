package com.financial.bank.acount.management.server.adapter;

import com.financial.bank.acount.management.domain.model.Statement;
import com.financial.bank.acount.management.domain.port.server.StatementTransaction;
import org.springframework.stereotype.Component;

import com.financial.bank.acount.management.domain.model.Account;
import com.financial.bank.acount.management.domain.port.server.SaveAccount;
import com.financial.bank.acount.management.domain.port.server.RetrieveAccount;

import java.util.List;

@Component
public class AccountRepository implements SaveAccount,RetrieveAccount, StatementTransaction {

    private SpringDataAccountRepository repository;

    public AccountRepository(SpringDataAccountRepository repository) {
        this.repository = repository;
    }

    public void save(Account account) throws Exception {
        repository.save(account);
    }

    public Account getAccount(String accountNumber) throws Exception {
        return repository.findAccountByNumber(accountNumber);
    }

    @Override
    public List<Statement> getStatement(String accountNumber) throws Exception {
        return repository.findTransactionByAccountNumber(accountNumber);
    }

    @Override
    public void saveTransaction(Statement statementTransaction) throws Exception {
        repository.saveTransaction(statementTransaction);
    }
}
