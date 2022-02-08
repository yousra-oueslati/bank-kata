package com.financial.bank.acount.management.domain.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.financial.bank.acount.management.domain.model.Account;
import com.financial.bank.acount.management.domain.model.Statement;
import com.financial.bank.acount.management.domain.model.TransactionTypeEnum;
import com.financial.bank.acount.management.domain.port.client.Deposit;
import com.financial.bank.acount.management.domain.port.client.AccountStatement;
import com.financial.bank.acount.management.domain.port.client.Withdrawal;
import com.financial.bank.acount.management.domain.port.server.SaveAccount;
import com.financial.bank.acount.management.domain.port.server.RetrieveAccount;
import com.financial.bank.acount.management.domain.port.server.StatementTransaction;
import org.h2.util.StringUtils;


public class AccountService implements Deposit, Withdrawal, AccountStatement {

    private RetrieveAccount retrieveAccount;
    private SaveAccount saveAccount;
    private StatementTransaction statementTransaction;

    public AccountService(RetrieveAccount retrieveAccount, SaveAccount saveAccount, StatementTransaction statementTransaction) {
        this.retrieveAccount = retrieveAccount;
        this.saveAccount = saveAccount;
        this.statementTransaction = statementTransaction;
    }

    public void deposit(String accountNumber, BigDecimal depositAmount) throws Exception {
        if(this.isAccountNumber(accountNumber) && depositAmount!=null) {
            Account account = retrieveAccount.getAccount(accountNumber);
            this.depositAmount(depositAmount, account);
            Statement statementDeposit = new Statement(accountNumber, account.getBalance(), TransactionTypeEnum.DEPOSIT, LocalDateTime.now(), depositAmount);
            saveAccount.save(account);
            statementTransaction.saveTransaction(statementDeposit);
        }
    }

    public boolean withdraw(String accountNumber, BigDecimal withdrawalAmount) throws Exception {
        if(this.isAccountNumber(accountNumber) && withdrawalAmount!=null){
            Account account = retrieveAccount.getAccount(accountNumber);
            boolean withdrawResult = this.withdrawAmount(withdrawalAmount, account);
            Statement statementWithdrawal = new Statement(accountNumber, account.getBalance(), TransactionTypeEnum.WITHDRAWAL, LocalDateTime.now(), withdrawalAmount);
            if (withdrawResult) {
                saveAccount.save(account);
                statementTransaction.saveTransaction(statementWithdrawal);
            }
            return withdrawResult;
        }
        return false;
    }

    public List<Statement> getAccountStatement(String accountNumber) throws Exception {
        return this.isAccountNumber(accountNumber) ? this.statementTransaction.getStatement(accountNumber):null;
    }

    private boolean withdrawAmount(BigDecimal withdrawalAmount, Account account) {
        if (account.getBalance().compareTo(withdrawalAmount) < 0) {
            return false;
        }
        account.setBalance(account.getBalance().subtract(withdrawalAmount));
        return true;
    }

    private void depositAmount(BigDecimal depositAmount, Account account) {
        account.setBalance(account.getBalance().add(depositAmount));
    }

    private boolean isAccountNumber(String accountNumber){
        // I supposed that an account number is an alphanumeric with the size 11
        String pattern= "[a-zA-Z0-9]{11}";
        return (!StringUtils.isNullOrEmpty(accountNumber)) && accountNumber.matches(pattern);
    }

}