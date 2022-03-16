--DDL 
CREATE TABLE typetest(
	name VARCHAR(9) PRIMARY KEY,
	money NUMBER NOT NULL
)

--DML , INSERT
INSERT INTO typetest(name, money) VALUES('ABCDEFGHIJ',100);
INSERT INTO typetest(name, money) VALUES('ABCDEFGHI',100);
INSERT INTO typetest(name, money) VALUES('아이유',100);
--한글은 1자 = 3bytes
INSERT INTO typetest(name, money) VALUES('박보검님',100);

--DML, UPDATE(누적)
UPDATE typetest SET money=money+200 WHERE name = '아이유';

SELECT money FROM typetest WHERE name ='아이유';

DROP TABLE typetest;

--DDL, ALTER(table 정보_table명, column명, 제약조건 등 변경)
CREATE TABLE alter_test(
	id VARCHAR(100) PRIMARY KEY,
	hit NUMBER DEFAULT 0
/*
 * DEFAULT : 별도 INSERT하지 않으면 초기값이 설정값으로 저장
 */
)

INSERT INTO alter_test(id) VALUES('java');

SELECT * FROM alter_test;

--alter : table 명 변경
ALTER TABLE alter_test RENAME TO alter_test2;
--alter : column 명 변경
ALTER TABLE alter_test2 RENAME COLUMN hit TO count;

SELECT * FROM alter_test2;
