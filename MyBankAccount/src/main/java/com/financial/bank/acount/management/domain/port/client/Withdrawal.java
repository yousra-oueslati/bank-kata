package com.financial.bank.acount.management.domain.port.client;

import java.math.BigDecimal;

public interface Withdrawal {

    boolean withdraw(String accountNumber, BigDecimal withdrawalAmount) throws Exception;
}
