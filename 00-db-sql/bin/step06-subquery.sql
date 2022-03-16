/*
 * Subquery - SQL내 SQL
 * Query 간결화를 위한 구문작성법 중 하나
 */
--subQuery 
SELECT * FROM product;

--최고가에 해당하는 상품의 name 조회(2단계)
SELECT MAX(price) FROM product;
SELECT name FROM product WHERE price = 2000;

--최고가에 해당하는 상품의 name 조회(subquery)
SELECT name FROM product WHERE price = (SELECT MAX(price) FROM product);

--전체상품들 중 상품의 평균가보다 높은 상품들 중, 가장 낮은 가격의 상품 조회
--1) SELECT ROUND(AVG(price)) FROM product --반올림
--2) SELECT name, maker. price FROM product WHERE price> (SELECT ROUND(AVG(price)) FROM product)
SELECT MIN(price) FROM product WHERE price >(SELECT ROUND(AVG(price)) FROM product);
SELECT name, maker, price 
FROM product 
WHERE price = (
SELECT MIN(price) FROM product WHERE price >(SELECT ROUND(AVG(price)) FROM product)
);

---------new table and queries----------

CREATE TABLE s_employee(
	empno NUMBER PRIMARY KEY,
	name VARCHAR2(100) NOT NULL,
	job VARCHAR2(100) NOT NULL,
	salary NUMBER NOT NULL
)

CREATE SEQUENCE s_employee_seq;

INSERT INTO s_employee(empno,name,job,salary) VALUES(s_employee_seq.nextval, '아이유', '개발', 700);
INSERT INTO s_employee(empno,name,job,salary) VALUES(s_employee_seq.nextval, '유재석', '총무', 600);
INSERT INTO s_employee(empno,name,job,salary) VALUES(s_employee_seq.nextval, '박보검', '개발', 900);
INSERT INTO s_employee(empno,name,job,salary) VALUES(s_employee_seq.nextval, '이상순', '총무', 600);
INSERT INTO s_employee(empno,name,job,salary) VALUES(s_employee_seq.nextval, '강하늘', '개발', 900);
INSERT INTO s_employee(empno,name,job,salary) VALUES(s_employee_seq.nextval, '손흥민', '총무', 900);

SELECT * FROM s_employee;

--sub query : 개발 job 사원 중 가장 높은 salary를 받는 사람의 name과 salary 정보 조회
SELECT name, salary, job
FROM s_employee
WHERE salary = (SELECT MAX(salary) FROM s_employee WHERE job='개발') AND job = '개발';