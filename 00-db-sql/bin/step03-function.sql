--DDL

/*
 * column : id, name, maker, price
 * datatype : id(number), price(number), name(varchar2(100)), maker(varchar2(100))
 * constraint : id(primary key), name/maker(not null), price(default 0)
 */

CREATE TABLE product(
	id NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	maker VARCHAR2(100) NOT NULL,
	price NUMBER DEFAULT 0
)

--DML
INSERT INTO product(id, name, maker, price) VALUES(1, '불닭볶음면', '삼양', 1500);
INSERT INTO product(id, name, maker, price) VALUES(2, '진라면', '오뚜기', 1100);
INSERT INTO product(id, name, maker, price) VALUES(3, '테라', '하이트진로', 1800);
INSERT INTO product(id, name, maker, price) VALUES(4, '참이슬', '하이트진로', 1300);	

--function1, column 개수
SELECT COUNT(*) FROM product;
--해당 속성에 대한 최소, 최대, 평균값
SELECT MIN(price) FROM product;
SELECT MAX(price) FROM product;
SELECT AVG(price) FROM product;

--오름차순(ASC), 내림차순(DESC) 정렬 - 기본 정렬값은 오름차순
SELECT name, price FROM product ORDER BY price DESC;

--특정조건의 column을 정렬하여 조회
SELECT id, name, price FROM product WHERE maker='하이트진로' ORDER BY price desc;

-- price가 1300~1700의 상품의 name, price 조회
SELECT name, price FROM product WHERE price<1700 AND price>1300 ORDER BY price ASC;
SELECT name, price FROM product WHERE price BETWEEN 1300 AND 1700 ORDER BY price ASC;

--primary key를 이용하여 해당 상품의 존재 유무 확인
SELECT COUNT(*) FROM product WHERE id =2;

--조회하는 column의 별칭
SELECT MIN(price) AS 최저가 FROM product;

--특정 column을 중복없이 조회
SELECT maker FROM product;
SELECT DISTINCT maker FROM product;

--데이터 일부가 포함되는 정보를 검색(LIKE %), %의 의미는 0개 이상의 문자열을 의미
SELECT * FROM product WHERE name LIKE '%라%';


SELECT * FROM product;