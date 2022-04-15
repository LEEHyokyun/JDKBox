--TABLE create
CREATE TABLE guestbook(
	guest_no NUMBER PRIMARY KEY,
	title VARCHAR(100) NOT NULL,
	content VARCHAR(100) NOT NULL
)

--create sequence
--sequence는 수정없음, 삭제만.
CREATE SEQUENCE guestbook_seq;

--sequence 적용
INSERT INTO guestbook(guest_no, title, content) VALUES(guestbook_seq.nextval, '즐목', '즐거운 목요일입니다');
UPDATE table SET a = 1 WHERE id = ~
/*
 * INESET로 update를 하되, sequence.nextval, 즉 별도의 객체와 메소드를 통해 진행
 */
--table 확인
SELECT * FROM guestbook;

SELECT GUEST_NO, TITLE, CONTENT FROM guestbook ORDER BY GUEST_NO DESC;

SELECT GUEST_NO, TITLE, CONTENT FROM guestbook WHERE GUEST_NO BETWEEN 2 AND 4;

COMMIT;

--LIKE
SELECT GUEST_NO, TITLE, CONTENT FROM guestbook 
WHERE TITLE LIKE '%금%'

--문자열 합치기
SELECT '불'||'금' FROM dual;
