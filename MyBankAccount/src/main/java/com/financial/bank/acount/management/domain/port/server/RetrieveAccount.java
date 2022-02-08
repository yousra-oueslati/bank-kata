package com.financial.bank.acount.management.domain.port.server;

import com.financial.bank.acount.management.domain.model.Account;

public interface RetrieveAccount {

    Account getAccount(String accountNumber) throws Exception;
}