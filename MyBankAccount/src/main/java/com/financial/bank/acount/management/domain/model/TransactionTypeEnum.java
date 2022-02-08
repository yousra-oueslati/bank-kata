package com.financial.bank.acount.management.domain.model;

public enum TransactionTypeEnum {
    DEPOSIT  ("deposit"),
    WITHDRAWAL("withdrawal");

    String transactionType;
    TransactionTypeEnum(String transactionType) {
        this.transactionType = transactionType;
    }
    public static TransactionTypeEnum getEnumTransactionByString(String type){
        if("deposit".equalsIgnoreCase(type)) return DEPOSIT;
        if("withdrawal".equalsIgnoreCase(type)) return WITHDRAWAL;
        return null;
    }

    @Override
    public String toString() {
        return transactionType;
    }

}
