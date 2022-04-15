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

SELECT NAME, ACCOUNT_NO FROM account WHERE ACCOUNT_NO = 3;

SELECT COUNT(*) FROM account WHERE ACCOUNT_NO = 3;

UPDATE account SET BALANCE = BALANCE + 10 WHERE ACCOUNT_NO = 1

SELECT ACCOUNT_NO, NAME, PASSWORD, BALANCE FROM account WHERE BALANCE = (SELECT MAX(BALANCE) FROM account);
SELECT MIN(price) FROM product WHERE price > (SELECT AVG(price) FROM product)
