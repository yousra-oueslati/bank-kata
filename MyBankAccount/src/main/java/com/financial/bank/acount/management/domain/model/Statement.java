package com.financial.bank.acount.management.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Statement {
    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal accountBalance;
    private TransactionTypeEnum transactionType;
    private LocalDateTime transactionDate;

    public Statement(String accountNumber, BigDecimal accountBalance, TransactionTypeEnum transactionType, LocalDateTime transactionDate, BigDecimal amout) {
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.amount = amout;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public TransactionTypeEnum getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypeEnum transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
