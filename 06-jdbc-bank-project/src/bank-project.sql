CREATE TABLE account(
	account_no NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
 	password VARCHAR2(100) NOT NULL,
	balance NUMBER NOT NULL
)

--DDL
CREATE SEQUENCE account_seq;

--DML

--1. 계좌개설
INSERT INTO account(account_no, name, password, balance) VALUES(account_seq.nextval, '아이유', '1234', 1000);
--2. 

--3. 

SELECT * FROM account;