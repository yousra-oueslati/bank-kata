package com.financial.bank.acount.management.domain.service;

import com.financial.bank.acount.management.domain.model.Account;
import com.financial.bank.acount.management.domain.model.Statement;
import com.financial.bank.acount.management.domain.model.TransactionTypeEnum;
import com.financial.bank.acount.management.server.adapter.AccountRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
    Account account;

    @InjectMocks
    @Spy
    AccountService accountService ;

    @Mock
    AccountRepository accountRepository ;

    List<Statement> transactions = new ArrayList<>();

    @org.junit.Before
    public void setUp() throws Exception {
        this.account = new Account("0500013M026", new BigDecimal(5000));
        accountService = new AccountService(accountRepository,accountRepository,accountRepository);

        Class classToTest = AccountService.class;
        Field reader = classToTest.getDeclaredField("retrieveAccount");
        reader.setAccessible(true);
        reader.set(accountService,accountRepository);

        transactions.add(new Statement("0500013M026",new BigDecimal(5100), TransactionTypeEnum.DEPOSIT, LocalDateTime.now(),
                new BigDecimal(100)));
        transactions.add(new Statement("0500013M026",new BigDecimal(4900), TransactionTypeEnum.WITHDRAWAL, LocalDateTime.now(),
                new BigDecimal(100)));

    }

    @Test
    public void deposit() throws Exception {
        BigDecimal balance = this.account.getBalance();
        Mockito.when(accountRepository.getAccount(Mockito.anyString())).thenReturn(this.account);
        Mockito.doNothing().when(this.accountRepository).save(this.account);
        this.accountService.deposit("0500013M026",new BigDecimal(200));
        Assert.assertTrue(this.account.getBalance().compareTo(balance)==1);
        Assert.assertEquals(this.account.getBalance().intValue(),5200);
    }

    @Test
    public void withdraw() throws Exception {
        BigDecimal balance = this.account.getBalance();
        Mockito.when(accountRepository.getAccount(Mockito.anyString())).thenReturn(this.account);
        Mockito.doNothing().when(this.accountRepository).save(this.account);
        boolean result =  this.accountService.withdraw("0500013M026",new BigDecimal(100));
        Assert.assertTrue(result);
        Assert.assertTrue(this.account.getBalance().compareTo(balance)==-1);
        Assert.assertEquals(this.account.getBalance().intValue(),4900);
    }

    @Test
    public void getStatement() throws Exception {
        Mockito.when(accountRepository.getStatement(Mockito.anyString())).thenReturn(this.transactions);
        List<Statement> statementLst =  this.accountService.getAccountStatement("0500013M026");
        Assert.assertNotNull(statementLst);
        Assert.assertEquals(statementLst.size(),2);

    }
}