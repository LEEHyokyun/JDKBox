/*
DB Modeling

ㅁ requirements
 주식거래시스템 구현
 주식정보는 주식명과 주당가격으로 구성
 현재 공시된 모든 주식정보는 고객에게 리스트로 제공되어야 한다.
 고객정보는 아이디, 패스워드, 이름, 주소로 구성
 고객이 시스템에 로그인하면 주소와 이름이 화면에 제공된다.
 고객이 배당받은 주식정보(주식명, 주당가격, 배당수량, 전체총액)가 리스트로 제공된다.
 공시된 주식 정보를 통해 매수와 매도가 가능하다.

ㅁ 교차엔티티
 고객과 주식은 다대다 관계
 	ㄴ 한명의 고객은 다수의 주식을 배당받고, 하나의 주식은 다수의 고객에게 배당받을 수 있다.
 다대다(many to many) 관계는 교차엔티티(Intersection Entity)로 해결한다.
 	ㄴ CUSTOMER |----|0<---SHARES ---->0>-----|STOCK
 ㅁ 복합키(PK)
 두개 이상의 요소(FK)가 PK를구성하는 경우를 말한다.
**/

--고객정보
CREATE TABLE customer(
	id VARCHAR2(100) PRIMARY KEY,
	password VARCHAR2(100) NOT NULL,
	name VARCHAR2(100) NOT NULL,
	address VARCHAR2(100) NOT NULL
)

--주식정보
CREATE TABLE stock(
	symbol VARCHAR2(100) 	PRIMARY KEY,
	price NUMBER NOT NULL
)

--교차엔티티(id, symbol은 복합PK)
CREATE TABLE shares(
	id VARCHAR2(100),
	symbol VARCHAR2(100),
	quantity NUMBER NOT NULL,
	CONSTRAINT fk_customer_id FOREIGN KEY(id) REFERENCES CUSTOMER(id),
	CONSTRAINT fk_symbol_id FOREIGN KEY(symbol) REFERENCES STOCK(symbol),
	CONSTRAINT pk_shares PRIMARY KEY(id, symbol)
)

--data insert(고객정보 및 주식정보)
INSERT INTO customer(id, password, name, address) VALUES('java', 'a', '아이유', '오리');
INSERT INTO customer(id, password, name, address) VALUES('spring', 'a', '강하늘', '종로');

INSERT INTO stock(symbol, price) VALUES('삼성', 2000);
INSERT INTO stock(symbol, price) VALUES('LG', 2500);
INSERT INTO stock(symbol, price) VALUES('현대', 3000);

COMMIT

SELECT * FROM customer;
SELECT * FROM stock;

--CONSTRAINT test
INSERT INTO shares(id, symbol, quantity) VALUES('java', '삼성', 2);
INSERT INTO shares(id, symbol, quantity) VALUES('java', '현대', 5);
INSERT INTO shares(id, symbol, quantity) VALUES('java', 'LG', 10);

INSERT INTO shares(id, symbol, quantity) VALUES('spring', 'LG', 3);

--update
UPDATE shares SET quantity = quantity + 3 WHERE id='java' and symbol='삼성' 
UPDATE shares SET quantity = quantity + 3 WHERE id='java' and symbol='삼성' 
SELECT * FROM shares;

--복합PK에 대해 동일한 값들이 적재되어있는 상태에서 중복될 수 없다.
INSERT INTO shares(id, symbol, quantity) VALUES('java', '삼성', 2);

--JOIN 연습
SELECT c.id, c.password, c.name, c.address, s.symbol, s.price, sh.quantity
FROM customer c, shares sh, stock s
WHERE c.id=sh.id and sh.symbol = s.symbol;

SELECT c.id, c.password, c.name, c.address, s.symbol, s.price, sh.quantity
FROM shares sh
INNER JOIN customer c ON c.id = sh.id
INNER JOIN stock s ON s.symbol = sh.symbol
WHERE c.id = 'java';