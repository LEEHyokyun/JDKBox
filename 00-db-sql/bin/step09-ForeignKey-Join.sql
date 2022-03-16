/*
		Foreign Key 제약조건과 Join SQL 
		
		- ERD ( Entity Relationship Diagram ) : 관계형 데이터베이스 설계를 위한 다이어그램 
		
		- 정규화 ( Normalization ) : 데이터베이스 설계시 중복을 최소화하고 무결성을 보장하기 위해 데이터를 구조화하는 작업 
									   ( 1, 2, 3 정규화에 대해 이후 공부 예정 ) 
		
		- Foreign Key 제약조건 :  참조 무결성 보장을 위한 제약조건 , 다른 테이블의 정보를 참조할 때 지정해야 하는 제약조건 
						             ex)  부모테이블 : 부서 ( 참조 대상이 되는 테이블 ) 
						             	   자식테이블 : 사원 ( 부서를 참조하는 테이블 ) 
						             	   				-> 사원의 부서번호 컬럼에 Foreign Key 제약조건을 명시한다
						             	  사원 테이블에 사원 정보가 저장되기 위해서는 반드시 부서테이블에 저장되어 있는 부서번호로만
						             	  저장될 수 있다 
						             	  즉 부서테이블에 존재하지 않는 부서번호로 사원테이블에 사원정보의 부서번호로 저장될 수 없다 
						             	  
		- Join SQL : 여러 테이블의 정보를 결합하여 조회하기 위한 SQL 	( 이번에는 기본 join 만 연습하고 상세한 것은 이후 공부 예정 )     
		
			   				 				
		사례)   사원정보관리 
		
			   	사원번호, 사원명, 직종, 월급 , 부서번호, 부서명 , 근무지, 부서대표번호 
			   	
			   	실제데이터
			   	1 		  아이유   개발  500   10			연구개발  판교   031
			   	2         박보검   개발  700   10        연구개발  판교   031			 					   				 					   				 					   				 					
			   				 					   				 						   				 					   				 								 					   				 					   				 					   				 							 					   				 					   				 					   				 				
			   	위와 같은 형태로 테이블을 설계해서 운영하면 사원에 대한 부서정보들이 중복되어 저장된다 		 					   				 				
			   	또한 부서정보가 업데이트되면 사원수만큼 다수의 데이터가 변경되어야 한다 			 					   				 					   				 					   				 					   				 					   				 				
				정규화 과정을 거치면 
				
				부서 테이블 |-----0|<- 사원 테이블 
				
				하나의 부서는 사원을 0 or 1 or 다수를 가질 수 있다 
				한명의 사원은 하나의 부서에 속해 있다 
				
	            이렇게 테이블을 분리하여 관리할 때는 참조 무결성 보장을 위해 사원의 부서번호 컬럼에 제약조건 Foreign Key를 지정해야 함 					             	   				 				 						             	   				 				 						             	   				 				 				
	*/	 				 				 				
						             	   				 				 				 				 				 				
	-- 테이블 생성 순서는 참조대상이 되는 부모 테이블부터 만들어야 한다 
	
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

--FOREIGN KEY : CONSTRAINT~

--참조무결성 : 반드시 사원은 하나의 부서를 가지고 있어야 한다.
--부서가 이미 생성된 상태, 부서번호가 존재한 상태에서 자식테이블인 사원 테이블이 생성되어야한다.
INSERT INTO k_employee(empno, ename, sal, job, deptno) VALUES(1, '아이유', 500, '개발', 10);

INSERT INTO department(deptno, dname, loc, tel) VALUES(10, '연구개발부', '오리', '031');
INSERT INTO department(deptno, dname, loc, tel) VALUES(20, '공공사업부', '종로', '02');
INSERT INTO department(deptno, dname, loc, tel) VALUES(30, '전략기획부', '판교', '031');

INSERT INTO k_employee(empno, ename, sal, job, deptno) VALUES(2, '박보검', 400, '개발', 10);
INSERT INTO k_employee(empno, ename, sal, job, deptno) VALUES(3, '강하늘', 700, '총무', 20);

INSERT INTO k_employee(empno, ename, sal, job, deptno) VALUES(4, '김태리', 700, '총무', 10);

--참조무결성 제약조건 위반으로 인한 생성불가
--INSERT INTO k_employee(empno, ename, sal, job, deptno) VALUES(4, '김태리', 700, '개발', 40);

SELECT * FROM department;
SELECT * FROM k_employee;

COMMIT

--사원번호가 1인 사원의 사원명 월급 직종 //사원 table - 부서명 근무지 //부서 table을 조회한다.
--1. key point > FK를 통한 table 결합 및 사원번호 조건절(*JOIN시 FK-PK를 결합, 즉 "JOIN")
SELECT  e.empno, e.ename, e.sal, e.job, d.dname, d.loc
FROM department d, k_employee e
WHERE d.deptno=e.deptno AND e.empno = 1

--2. 
SELECT  e.empno, e.ename, e.sal, e.job, d.dname, d.loc
FROM department d --부모 table
INNER JOIN k_employee e ON d.deptno = e.deptno
WHERE e.empno = 1








									   							   							   							   							   							   
