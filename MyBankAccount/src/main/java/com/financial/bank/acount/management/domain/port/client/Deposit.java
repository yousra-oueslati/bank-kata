package com.financial.bank.acount.management.domain.port.client;

import java.math.BigDecimal;

public interface Deposit {

    void deposit(String accountNumber, BigDecimal depositAmount) throws Exception;
}