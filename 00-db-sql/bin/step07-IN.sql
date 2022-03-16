/*
 * IN 연산자
 * - 특정값이 포함되는 데이터를 조회하고자 할 때 사용하는 연산자.
 * 
 * SELECT ~
 * FROM ~
 * WHERE 조건 IN('데이터', '데이터') 
 * -> column = 조건 = IN조건에 부합하는 항목들
 * 
 * NOT IN 
 * -> 특정값이 포함되지 않고자 할 경우
 * 
 */


create table food(
	id number primary key,
	name varchar2(100) not null,
	maker varchar2(100) not null,
	price number not null
)

create sequence food_seq;

insert into food(id,name,maker,price) values(food_seq.nextval,'후라이드','또래오래',15000);
insert into food(id,name,maker,price) values(food_seq.nextval,'소곱창','대한곱창',20000);
insert into food(id,name,maker,price) values(food_seq.nextval,'양념치킨','또래오래',16000);
insert into food(id,name,maker,price) values(food_seq.nextval,'참치회','이춘복참치',35000);
insert into food(id,name,maker,price) values(food_seq.nextval,'파닭','또래오래',17000);
insert into food(id,name,maker,price) values(food_seq.nextval,'미니전골','대한곱창',18000);

commit

SELECT * FROM food;
SELECT COUNT(*) FROM food;

--maker가 농심 또는 오뚜기인 상품들을 조회
SELECT id, name, maker,price
FROM food
WHERE maker IN('대한곱창', '이춘복참치') --조건절에서의 or 등의 표현을 간결하게, subQuery로 나타낼 수 있다.
--포함 안되어 있다면? NOT IN
SELECT id, name, maker,price
FROM food
WHERE maker NOT IN('대한곱창', '이춘복참치') --조건절에서의 or 등의 표현을 간결하게, subQuery로 나타낼 수 있다.
-- = maker <> '대한곱창' AND maker <> '이춘복참치'

/*
 * key point : IN표현을 통해 간결하게, subquery를 명확히 표현하도록 할 수 있다.
 */
SELECT AVG(PRICE) FROM food

--key point : 전체음식 평균가보다 maker별 음식평균가가 낮은 maker 조회
SELECT maker
FROM food
GROUP BY maker
HAVING AVG(PRICE) < (SELECT AVG(PRICE) FROM food)
/*
 * 위 구문을 통해 maker를 특정
 * 이 특정한 maker를 IN에 사용
 * "IN의 key point는, WHRE에 직접 비교연산자를 사용하는 것이 아니라, IN을 통해 subQuery를 사용할 수 있다는 것"
*/

SELECT maker, name, price
FROM food
WHERE maker IN (
	SELECT maker
	FROM food
	GROUP BY maker
	HAVING AVG(PRICE) < (SELECT AVG(PRICE) FROM food)
)
ORDER BY price desc

-- job별 사원수가 3명 이상인 job에 해당하는 사원의 name, job을 조회, empno desc.
SELECT name, job, empno
FROM s_employee
WHERE job IN(
	SELECT job
	FROM s_employee
	GROUP BY job
	HAVING COUNT(*) >= 3
)
ORDER BY EMPNO DESC

SELECT * FROM s_employee