DROP TABLE IF EXISTS account;

CREATE TABLE account (
  accountNumber CHAR(27) NOT NULL,
  balance Numeric NOT NULL
);
CREATE TABLE statement (
  accountNumber CHAR(27) NOT NULL,
  accountBalance Numeric NOT NULL,
  transactionType CHAR(10) NOT NULL,
  transactionDate TIMESTAMP NOT NULL,
  transactionAmout Numeric NOT NULL
);
Insert into account values (5,5000);