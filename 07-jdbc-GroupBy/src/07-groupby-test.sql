--test for employee
SELECT job, COUNT(*), MAX(SALARY)
FROM s_employee
GROUP BY job
ORDER BY MAX(SALARY) DESC;

--test for product
INSERT INTO product(ID, NAME, MAKER, PRICE) VALUES(7,'새우깡', '농심', 1000);
INSERT INTO product(ID, NAME, MAKER, PRICE) VALUES(8,'양파깡', '농심', 900);

SELECT MAKER, AVG(PRICE)
FROM product
GROUP BY MAKER
HAVING AVG(PRICE) < (SELECT AVG(PRICE) FROM product) --가장 먼저 실행되는 구문
ORDER BY AVG(PRICE) DESC

SELECT * FROM s_employee