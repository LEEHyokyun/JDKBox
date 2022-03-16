--Join test
CREATE TABLE  department(
	deptno NUMBER PRIMARY KEY,
	dname VARCHAR2(100) NOT NULL,
	loc VARCHAR2(100) NOT NULL,
	tel VARCHAR2(100) NOT NULL
)
									   							   
CREATE TABLE k_employee(
    empno NUMBER PRIMARY KEY,
    ename VARCHAR2(100) NOT NULL,
    sal NUMBER NOT NULL,
    job VARCHAR2(100) NOT NULL,
    deptno NUMBER NOT NULL,
    CONSTRAINT fk_deptno FOREIGN KEY(deptno) REFERENCES department(deptno)
)

--empno3에 해당하는 사원의 empno, ename, sal, job, 부서정보인 deptno, dname, loc, tel 조회
SELECT * FROM department

--JOIN 순서(부모/자식)는 상관없다. 결합만 할 수 있으면 된다.
SELECT e.empno, e.ename, e.sal, e.job, d.deptno, d.dname, d.loc, d.tel
FROM department d
INNER JOIN k_employee e ON d.deptno = e.deptno
WHERE e.empno = 3