ERD ( Entity Relationship Diagram ) 
: 개체 관계 모델링 즉 데이터 모델링을 위한 다이어그램 ​

ERWIN : ERD(entity-relationship diagram) Tool​

File -> New -> Logical/Physical 버튼 선택 
-> Logical 데이터 모델링부터 구성 

​ERWIN 환경 설정 
메뉴 Model - Model Propertie 선택
Model Properties 대화상자
- 세번째 Notation탭에서 Logical Notation Physical Notation영역 모두 IE옵션 버튼을 선택
- 네번째 Default 탭에서 VARCHAR2(100) / NOT NULL.
​
Entity명
----------
기본키
----------
일반속성영역

​
이미지 추출 
메뉴 Tools - Report Template Builder - Report Builder - Report Templates - Picture 선택후 화살표 클릭
- File -run - brower 뜨면 이미지 다운 가능

HTML 안나올때는 경로 변경
Report Template Builder -> Edit -> Properties ->Export탭-> Generated File 위치변경

​

​

Physical 모드에서 컬럼타입보기

DataType display
Format Tab -> Table Display -> Column DataType 

​
기본데이터타입변경 
Model Tab -> Model Properties -> Defaults -> VARCHAR2(100) 으로 변경

​
ERD ( Entity Relationship Diagram ) 
: 개체 관계 모델링 , 관계형 데이터베이스 설계를 위한 다이어그램 
	ㄴ table = entity = primary key + 기본 속성(attribute)
	ㄴ 데이터 중복..수정 및 유지보수가 어렵기 때문에, 사원은 부서번호만 부서번호에 따른 정보는 따로 저장
		ㄴ 부서번호는 FK, 외래키 혹은 참조키
		ㄴ 결국엔 부서번호에 대한 정보를 참조하는 방식
	ㄴ 이를 바탕으로 기존 table을 분할하는 작업 -> 정규화
	ㄴ 참조대상을 먼저 만들어야 하며(부서table-> 그 후 사원 table), 이 참조대상이 부모 table이 된다.


논리 데이터 모델링 
- 논리적인 데이터 관리 및 관계를 정의한 모델.
전체 업무 범위와 업무 구성요소를 정의하고 확인할 수 있다. 

​물리 데이터 모델링 
- 논리 데이터 모델을 DBMS 특성에 맞게 구체화시킨 모델을 말한다 
​

식별관계 (identified relationship): 부모 테이블의 기본키 혹은 복합키(이후 공부예정)가 
자식 테이블의 기본키 혹은 복합키의 구성원으로
전이되는 관계 ( ex - 사원과 신체정보 ) 
※ 사원 table(PK: 사원번호) - 부서 table(PK: 사원번호)에서..PK가 PK그대로 활용되는 경우
​※ 사원 테이블(부모)의 PK가 - 신체정보 테이블(자식)의 PK로 활용되면서 FK(외래키)로 활용된다.

비식별관계(non-identified relationship): 부모 테이블의 기본키 혹은 복합키가 
자식 테이블의 일반속성으로 전이되는 관계 
(ex - 부서와 사원정보 ) 
※ 사원 table(PK: 사원번호, FK: 부서번호) - 부서 table(PK: 부서번호)에서..부모 테이블에서는 일반 column(FK)로 저장하는 경우







