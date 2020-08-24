DELETE FROM RENT;
DELETE FROM MILEAGE;
DELETE FROM CUSTOMER;
DELETE FROM CAR;
DELETE FROM KIND;


-- 시퀀스
DROP SEQUENCE CTM_CTMNO_SEQ;
CREATE SEQUENCE CTM_CTMNO_SEQ
	 START WITH 1
     INCREMENT BY 1
     MINVALUE 0;

DROP SEQUENCE RENT_RENTNO_SEQ;
CREATE SEQUENCE RENT_RENTNO_SEQ
	 START WITH 1
     INCREMENT BY 1
     MINVALUE 0;
SELECT LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') FROM DUAL;
    
-- 고객
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '동자승', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '대구 서구 삼익뉴타운', '신규회원', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '홍길동', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '조선', '조선족', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '크리링', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '화성', '빡빡이', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '손오공', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '지구', '미친놈', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '배지터', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '지구', '미친놈 친구', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '손오반', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '지구', '미친놈 아들', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '크러쉬', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '서울', '딴따라', 0, 0);
INSERT INTO CUSTOMER VALUES (CTM_CTMNO_SEQ.NEXTVAL, '스펀지', '010-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0') || '-' || LPAD(TRUNC(DBMS_RANDOM.VALUE(1, 9999)), 4, '0'), '바닷속', '스펀지', 0, 0);

-- 마일리지
INSERT INTO MILEAGE VALUES (1, 1,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
INSERT INTO MILEAGE VALUES (2, 3,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
INSERT INTO MILEAGE VALUES (3, 5,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
INSERT INTO MILEAGE VALUES (4, 2,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
INSERT INTO MILEAGE VALUES (5, 2,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
INSERT INTO MILEAGE VALUES (6, 1,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
INSERT INTO MILEAGE VALUES (7, 7,1,TRUNC(DBMS_RANDOM.VALUE(1, 1000)) * 10, '랜덤 기입');
UPDATE CUSTOMER c SET CTM_MLG = (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = c.CTM_NO );
UPDATE CUSTOMER SET CTM_MLG = 0 WHERE CTM_MLG IS NULL;

-- 차종류
INSERT INTO KIND VALUES ('S', '소형');
INSERT INTO KIND VALUES ('M', '중형');
INSERT INTO KIND VALUES ('H', '승합차');
INSERT INTO KIND VALUES ('B', '버스');
INSERT INTO KIND VALUES ('J', '지프');

-- 차량
INSERT INTO CAR VALUES ('12하4219', '아반떼', 'S', 'LPG', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 60000, 5, '침수차량');
INSERT INTO CAR VALUES ('52하6437', '소렌토', 'M', 'LPG', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 70000, 5, '잘구른다');
INSERT INTO CAR VALUES ('38허4532', '스타렉스', 'H', 'LPG', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 80000, 8, '티라노 사우르스 렉스');
INSERT INTO CAR VALUES ('29허9435', '15인승', 'B', 'LPG', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 150000, 5, '운전기사 필수');
INSERT INTO CAR VALUES ('19호2362', '30인승', 'B', '휘발유', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 300000,10, '운전기사 필수');
INSERT INTO CAR VALUES ('06호8254', '지프지프', 'J', '휘발휘발', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 100000, 5, '내가 낸데');
	
-- 대여
INSERT INTO RENT VALUES (RENT_RENTNO_SEQ.NEXTVAL, 2, '12하4219', '2005/05/15', '2018/02/17', 1, 0, '절도범',0);
INSERT INTO RENT VALUES (RENT_RENTNO_SEQ.NEXTVAL, 5, '52하6437', '2015/11/15', '2018/02/17', 1, 0, '사고',0);
INSERT INTO RENT VALUES (RENT_RENTNO_SEQ.NEXTVAL, 1, '38허4532', '2018/11/15', '2019/02/10', 1, 1, '이상무',0);
INSERT INTO RENT VALUES (RENT_RENTNO_SEQ.NEXTVAL, 3, '29허9435', '2019/05/15', '2019/05/16', 1, 1, '빵상',0);
INSERT INTO RENT VALUES (RENT_RENTNO_SEQ.NEXTVAL, 7, '19호2362', '2019/07/12', '2019/07/15', 1, 0, '장발장',0);
INSERT INTO RENT VALUES (RENT_RENTNO_SEQ.NEXTVAL, 6, '06호8254', '2020/03/30', '2020/05/22', 1, 0, '차량파손',0);
UPDATE RENT SET RENT_TIME = TO_CHAR(TO_DATE(RETURN_DATE)-TO_DATE(RENT_DATE))*24;


-- 조회
SELECT * FROM RENT ORDER BY RENT_NO;
SELECT * FROM CUSTOMER ORDER BY CTM_NO;
SELECT * FROM CAR;
SELECT * FROM KIND;
SELECT * FROM MILEAGE;

SELECT * FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO WHERE c.ctm_no=1 ;
SELECT * FROM car NATURAL JOIN kind;


				
