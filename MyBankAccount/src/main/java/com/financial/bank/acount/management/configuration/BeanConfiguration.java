package com.financial.bank.acount.management.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.financial.bank.acount.management.Application;
import com.financial.bank.acount.management.domain.service.AccountService;
import com.financial.bank.acount.management.server.adapter.AccountRepository;

@Configuration
@ComponentScan(basePackageClasses = Application.class)
public class BeanConfiguration {

    @Bean
    AccountService bankAccountService(AccountRepository repository) {
        return new AccountService(repository, repository, repository);
    }

}
