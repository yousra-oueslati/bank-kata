package com.financial.bank.acount.management.domain.port.server;

import com.financial.bank.acount.management.domain.model.Statement;

import java.util.List;

public interface StatementTransaction {

    List<Statement> getStatement(String accountNumber) throws Exception;

    void saveTransaction(Statement statementTransaction) throws Exception;
}
