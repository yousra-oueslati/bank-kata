package com.financial.bank.acount.management.domain.port.server;

import com.financial.bank.acount.management.domain.model.Account;

public interface SaveAccount {

    void save(Account account) throws Exception;
}