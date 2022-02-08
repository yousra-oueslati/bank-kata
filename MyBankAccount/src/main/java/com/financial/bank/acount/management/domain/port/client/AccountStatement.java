package com.financial.bank.acount.management.domain.port.client;

import com.financial.bank.acount.management.domain.model.Statement;

import java.util.List;

public interface AccountStatement {

    List<Statement> getAccountStatement(String accountNumber) throws Exception;
}
