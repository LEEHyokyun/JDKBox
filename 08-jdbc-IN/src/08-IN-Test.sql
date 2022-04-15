--SQL Test
/*
	* s_employee table에 저장된 전체 사원의 월급 평균보다
	* 직종별(job) 평균 월급이 낮은 job 사원에 대하여
	* 해당 사원 정보를 조회하여 리스트로 반환받아 출력
*/

SELECT * FROM s_employee;

SELECT EMPNO, NAME, JOB, SALARY
FROM s_employee
WHERE JOB IN (
	SELECT JOB
	FROM s_employee
	GROUP BY JOB
	HAVING AVG(SALARY) < (SELECT AVG(SALARY) FROM s_employee)
)
ORDER BY SALARY DESC

--maker 농심, 오뚜기가 아닌