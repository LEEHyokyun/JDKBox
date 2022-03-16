/*
 * GroupBy , Having
 * 
 * ㅁ GroupBy : 테이블에서 특정 column을 기준으로 그룹화하여 검색할 경우에 사용
 * 		* 데이터를 원하는 group으로 분할
 * ㅁ Having : Group by와 함께 사용하는 조건절(그룹에 대한 조건을 지정)
 * 
 * 상품 테이블에서 "제조사별" 상품수 등..
 * 
 */

SELECT * FROM product;

/*
 * ****"특정 기준"을 기준으로하여, table을 정렬하고 분류하고자 할때
 */

--maker별 상품수
SELECT maker, COUNT(*) 
FROM product
GROUP BY maker

--maker별 상품수를 조회하되, 상품수 개수가 1개 초과인 경우
--조건절인 having 사용시 별칭을 사용하지 않는다.
SELECT maker, COUNT(*)
FROM product
GROUP BY maker
HAVING COUNT(*) > 1
ORDER BY COUNT(*) DESC

--maker별 상품평균가 조회 및 특정값(1550원)을 초과하는 조건절 추가 명기
SELECT maker, AVG(PRICE)
FROM product
GROUP BY maker
ORDER BY AVG(PRICE) DESC;

SELECT maker, AVG(PRICE)
FROM product
GROUP BY maker
HAVING AVG(PRICE) > 1550

--s_employee table
SELECT * FROM s_employee

--job이라는 GROUP화, job을 기준으로 job과 해당 사원수
SELECT job, COUNT(*)
FROM s_employee
GROUP BY job
ORDER BY COUNT(*) DESC

--job을 기준으로, job의 평균 월급이 700만원 이상인 데이터를 조회
SELECT job, AVG(SALARY)
FROM s_employee
GROUP BY job
HAVING AVG(SALARY) > 700
ORDER BY AVG(SALARY) DESC

--전체 사원의 평균 월급(763만원)보다 작은 job의 평균월급
SELECT job, AVG(SALARY)
FROM s_employee
GROUP BY job
HAVING AVG(SALARY) < (SELECT AVG(SALARY) FROM s_employee)
ORDER BY AVG(SALARY) DESC
