-- note : raw query
/*	JDBC
 * 	ㅁ Java database connectivity
 * 	ㅁ JAVA application과 db를 연결하기위한 기술로, spring framework를 사용하기 위한 기반이 된다.
 * 
 * SQL, Structure Query Language
 * DB에서 데이터를 정의, 조작, 제어
 * 
 * ㅁ DDL
 * 	* Data Definition Language, 데이터 정의 언어
 *	* CREATE(생성), DROP(삭제), ALTER(수정)
 *
 * 	ㅁ DML
 * 	* Data Manipulation Language, 데이터 조작어
 * 	* INSERT(삽입), SELECT(조회), UPDATE(수정), DELETE(삭제)
 * 
 * 	ㅁ DCL
 * 	* Data Control Language, 데이터 제어어
 * 	* GRANT(권한부여), REVOKE(권한취소), COMMIT(실제 DB에 반영)
 * 	* COMMIT(실제 DB에 반영), ROLLBACK(작업 취소 및 원상태로 회귀)
 * 
 * ㅁ Table 
 * 	* 데이터를 저장하는 공간
 * 	* COLUMN : 속성, attribute
 * 	* DATATYPE : 문자열(VARCHAR2, 가변 String), 숫자형(NUMBER), 
 * 	* CONSTRAINT : 제약조건
 * 		ㄴ Primary key : NOT NULL + UNIQUE
 * 	 	ㄴ NULL 허용X, 유일자
 * 	ㅁ SQL 실행 단축키 : 영역 지정후 ALT + X
 */

--DDL : 테이블 생성
CREATE TABLE member(
	ID VARCHAR(100) PRIMARY KEY,
	password VARCHAR2(100) NOT NULL,
	name VARCHAR2(100) NOT NULL,
	address VARCHAR2(100)
)

--DDL : 테이블 제거
--DROP TABLE member

--DML, 데이터 조회
SELECT * FROM MEMBER;
SELECT ID FROM MEMBER;

--DML, 조건 데이터조회
SELECT * FROM member WHERE id = 'java';

--DML, 데이터 삽입
INSERT INTO member(ID, password, name, address) VALUES('java','abcd','아이유','오리');
INSERT INTO member(ID, password, name, address) VALUES('spring','dcba','박보검','죽전');

--제약조건 테스트
/*
 * primary key : UNIQUE, NOT NULL .. 공란허용이 안되며, 반드시 입력해야하는 값
 * NOT NULL : 입력필수(공란허용X)
 */
INSERT INTO member(ID, password, name, address) VALUES('java','a','b','c');
INSERT INTO member(ID, password, name, address) VALUES('a','b','c');

-- DML : 데이터 업데이트
UPDATE member SET address = '강남' WHERE ID = 'java';

-- DML : 데이터 삭제
DELETE FROM member WHERE id = 'java';

-- table 확인
SELECT * FROM member;

--Data CRUD
--Create(INSERT), READ(Select), Update(Update), Delete(delete)

SELECT * FROM member

INSERT INTO member(ID, password, name, address) VALUES('angel', 'kind', '이상순', '애월읍');

SELECT id, password FROM member WHERE id='angel';

UPDATE member SET address = '오리' WHERE address='애월읍';

DELETE FROM member WHERE id='angel';