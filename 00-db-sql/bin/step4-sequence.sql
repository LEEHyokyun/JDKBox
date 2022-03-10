/* 
오라클 시퀀스(sequence)
: 순차적으로 증가, 유일한 값을 생성하기 위한 "객체" 
주로 primary key ( unique + not null ) 를 생성하기 위해 사용
테이블과는 독립적 구조 

CREATE SEQUENCE 시퀀스명 
[START WITH 시작번호]
[INCREMENT BY 증가값]
[MAXVALUE 최대값] 
[MINVALUE 최소값]
[ CYCLE or NOCYCLE ] 
[ NOCACHE ] 

​Oracle dual table 
: 오라클에서 제공하는 기본 테이블, admin 계정에 존재 
컬럼 하나만 존재 , 주로 시퀀스 또는 날짜함수, 산술연산에 사용
sys Admin 계정에서 관리하고 수정 및 삭제 등 조작은 불가 
*/

-- dual table
SELECT * FROM dual;
SELECT 2*5 FROM dual;
SELECT SYSDATE FROM dual;

-- sequence
CREATE SEQUENCE test_seq;

--dual table을 이용한 시퀀스 값
--nextval -- sequence "객쳬"의 다음 값을 생성 
SELECT test_seq.nextval FROM dual;

--시퀀스 삭제
DROP SEQUENCE test_seq;

--start with option
CREATE SEQUENCE test_seq START WITH 7;

--table에서의 활용
CREATE TABLE car(
	car_no NUMBER PRIMARY KEY,
	model VARCHAR(100) NOT NULL
)

CREATE SEQUENCE car_seq;

--sequence의 핵심은, 위 table에 data insert 시점에서 자동 인덱싱 카운트가 되도록.
INSERT INTO car(car_no, model) VALUES(car_seq.nextval, '소나타');
INSERT INTO car(car_no, model) VALUES(car_seq.nextval, 'SM6');

SELECT * FROM car;