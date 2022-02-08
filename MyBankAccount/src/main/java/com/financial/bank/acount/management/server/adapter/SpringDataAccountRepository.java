package com.financial.bank.acount.management.server.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.financial.bank.acount.management.domain.model.Statement;
import com.financial.bank.acount.management.domain.model.TransactionTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.financial.bank.acount.management.domain.model.Account;

@Repository
public class SpringDataAccountRepository extends JdbcDaoSupport {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void save(Account account) throws Exception {
        try {
            String sql = "UPDATE account SET " + "balance= ? WHERE accountNumber = ?";
            getJdbcTemplate().update(sql, new Object[]{account.getBalance(), account.getAccountNumber()});
        } catch (Exception exception){
            throw new Exception("Update account error");
        }
    }

    public Account findAccountByNumber(String accountNumber) throws Exception {
        Account account = null;
        try {
            String sqlRequest = "SELECT * FROM account WHERE accountNumber = ?";
            account = (Account) getJdbcTemplate().queryForObject(sqlRequest, new Object[]{accountNumber}, new RowMapper<Account>() {
                public Account mapRow(ResultSet resultSet, int rwNumber) throws SQLException {
                    return new Account(resultSet.getString("accountNumber"), resultSet.getBigDecimal("balance"));
                }
            });
        } catch (Exception exception) {
            throw new Exception("Select account by number error");
        }
        return account;
    }

    public List<Statement> findTransactionByAccountNumber(String accountNumber) throws Exception {
        List<Statement> transactions = null;
        String sql = "SELECT * FROM statement WHERE accountNumber = ?";
        try {
            transactions = (List<Statement>) getJdbcTemplate().query(sql, new RowMapper<Statement>() {
            public Statement mapRow(ResultSet resultSet, int rwNumber) throws SQLException {
            Statement account = new Statement(resultSet.getString("accountNumber"),
                    resultSet.getBigDecimal("accountBalance"),
                    TransactionTypeEnum.getEnumTransactionByString(resultSet.getString("transactionType")),
                    getTransactionDateToLocalDate(resultSet.getDate("transactionDate")),
                    resultSet.getBigDecimal("transactionAmout"));

            return account;
        }
    }, accountNumber);
        }catch(Exception exception){
            throw new Exception("Select account statement error");
        }
        return transactions;
    }

    public void saveTransaction(Statement statement) throws Exception {
        try {
            String sql = "INSERT INTO statement (accountNumber,accountBalance,transactionType,transactionDate,transactionAmout) values (?,?,?,?,?) ";
            getJdbcTemplate().update(sql, new Object[]{statement.getAccountNumber(), statement.getAccountBalance(), statement.getTransactionType().toString(), statement.getTransactionDate(), statement.getAmount()});
        } catch (Exception exception){
            throw new Exception("Insert transaction error");
        }
    }

    private LocalDateTime getTransactionDateToLocalDate(Date transactionDate) {
        return  Instant.ofEpochMilli(transactionDate.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

    }

}
