-----------------------------------------------------------------------------김창동-----------------------------------------------------------------------------
SELECT * FROM USER_TABLES ;

SELECT * FROM RENT;
SELECT * FROM CUSTOMER;
SELECT * FROM CAR;
SELECT * FROM KIND;
SELECT * FROM MILEAGE;

UPDATE MILEAGE SET MLG_KIND = 0 WHERE mlg_NO in(1,3,5,7);

SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER;

SELECT DISTINCT c.CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = c.CTM_NO ) AS MILEAGE
FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO;

				 
SELECT DISTINCT c.CTM_NO, c.CTM_NAME, c.TEL, c.ADDRESS, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = 1) AS MILEAGE, c.CTM_REMARK 
FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO
WHERE c.CTM_NO = 1;


SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG
FROM CUSTOMER
WHERE CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) ORDER BY CTM_NO; 

SELECT MLG_NO, CTM_NO, MLG_KIND, MILEAGE, MLG_REMARK FROM MILEAGE;

SELECT MLG_NO, CTM_NO, MLG_KIND, MILEAGE, MLG_REMARK 
  FROM MILEAGE 
 WHERE MLG_NO = 1; 

SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG
FROM CUSTOMER
WHERE CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) ORDER BY CTM_NO;

SELECT RENT_NO, C.CAR_KIND ,RENT_TIME * FARE * (100 - SALE) / 100 AS 최종요금
FROM RENT r LEFT OUTER JOIN CAR c ON r.CAR_NO = c.CAR_NO;

DELETE FROM RENT WHERE RENT_NO = 1;

-- rentLookupCarKind
SELECT c.CAR_KIND, SUM(RENT_TIME *FARE * (100 - SALE) / 100) 
FROM RENT r LEFT OUTER JOIN CAR c ON r.CAR_NO = c.CAR_NO
GROUP BY C.CAR_KIND
HAVING SUM(RENT_TIME * FARE * (100 - SALE) / 100) > 0;

SELECT c.CTM_NO ,CTM_MLG 
  FROM CUSTOMER c LEFT OUTER JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO ;

 SELECT * FROM MILEAGE;

SELECT DISTINCT c.*, (SELECT MILEAGE FROM MILEAGE m WHERE MLG_KIND = 1 and m.CTM_NO = mlg.CTM_NO ) - (SELECT MILEAGE FROM MILEAGE m WHERE MLG_KIND = 0 and m.CTM_NO = mlg.CTM_NO) 
  FROM CUSTOMER c LEFT OUTER JOIN MILEAGE mlg ON c.CTM_NO = mlg.CTM_NO ORDER by c.CTM_NO;

SELECT *
  FROM MILEAGE m
 WHERE MLG_KIND = 0;


UPDATE CUSTOMER SET CTM_NAME = ? + '(탈퇴)' WHERE CTM_NO = 1;
SELECT CTM_NAME FROM CUSTOMER WHERE CTM_NO = 1;


-----------------------------------------------------------------------------오수정-----------------------------------------------------------------------------
SELECT *
  FROM CUSTOMER ;

SELECT r.RENT_NO, car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK 
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO ;
 
SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK 
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO ;
 
SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE r.RENT_DATE >= '2020-04-29' AND r.RETURN_DATE <= SYSDATE;

SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE r.RENT_DATE >= '2020-04-29' AND r.RETURN_DATE <= '2020-10-22' AND car.CAR_NO LIKE '%' || '06' || '%';

SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE r.RENT_DATE >= '2020-04-29' AND r.RETURN_DATE <= '2020-10-22' AND c.CTM_NAME LIKE '%' || '손' || '%';

SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE r.RENT_DATE >= '2020-04-29' AND r.RETURN_DATE <= '2020-10-22' AND c.TEL LIKE '%' || '6418' || '%';

 
<<<<<<< HEAD
SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK, k.KIND_NAME 
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO JOIN KIND k ON car.CAR_KIND = k.CAR_KIND 
=======

SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
>>>>>>> refs/heads/OhsuJung
 WHERE c.CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1)
 ORDER BY r.RENT_NO;

INSERT INTO RENT values(8, 7, '06호8254', '2020/09/29', '2020/10/22', 1, 0, '차량파손',0);

UPDATE RENT 
   SET CTM_NO = 3, CAR_NO = '12하4219', RENT_DATE = '2020-08-25', RETURN_DATE = '2020-08-28', RENT_TIME = ((SYSDATE + 1) - SYSDATE) * 24, IS_DRIVER = 1, RENT_REMARK = '아아아아아악'
 WHERE RENT_NO = 7;

DELETE FROM RENT WHERE RENT_NO = 7;
 
SELECT *
  FROM RENT;
 
SELECT CAR_NO, CAR_NAME, k.CAR_KIND, k.KIND_NAME, FUEL, DISTANCE, FARE, SALE, CAR_REMARK FROM CAR c LEFT OUTER JOIN KIND k ON c.CAR_KIND = k.CAR_KIND
 WHERE CAR_NO IN (SELECT R.CAR_NO FROM RENT R LEFT OUTER JOIN CAR C ON R.CAR_NO = C.CAR_NO WHERE IS_RENT=1);
 
SELECT LIST_CTM 
  FROM CUSTOMER;
 
SELECT CAR_NO, CAR_NAME, k.CAR_KIND, k.KIND_NAME, FUEL, DISTANCE, FARE, SALE, CAR_REMARK FROM CAR c LEFT OUTER JOIN KIND k ON c.CAR_KIND = k.CAR_KIND ;
 
SELECT *
  FROM KIND ;
 
INSERT INTO CAR VALUES ('06호8258', '지프지sd프', 'J', '휘발휘발', TRUNC(DBMS_RANDOM.VALUE(1, 100000)), 100000, 5, '내가 낸데');

SELECT CAR_NO, CAR_NAME, k.CAR_KIND, k.KIND_NAME, FUEL, DISTANCE, FARE, SALE, CAR_REMARK 
  FROM CAR c LEFT OUTER JOIN KIND k ON c.CAR_KIND = k.CAR_KIND
 WHERE k.KIND_NAME = '승합차' AND CAR_NAME = '스타렉스';

SELECT * FROM car;

SELECT * FROM CUSTOMER;
DELETE FROM CUSTOMER WHERE CTM_NO = 12;
  
 SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK 
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE car.CAR_NO LIKE '%' || '12하4219' || '%' OR c.CTM_NAME LIKE '%' || '배지' || '%' OR c.TEL LIKE '%' || '' || '%'
ORDER BY r.RENT_NO;

-----------------------------------------------------------------------------김보현-----------------------------------------------------------------------------
SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER WHERE LIST_CTM = 1;
UPDATE CAR SET CAR_NAME = '스타' , CAR_KIND = 'S' , FUEL = '휘발유' , DISTANCE = 999 , FARE = 350000 , SALE = 5 , CAR_REMARK = '티라노' WHERE CAR_NO = '38허4532';
SELECT DISTINCT KIND_NAME  FROM KIND k LEFT OUTER JOIN CAR c ON c.car_kind = k.CAR_KIND;

-----------------------------------------------------------------------------배성덕-----------------------------------------------------------------------------
UPDATE MILEAGE SET MLG_KIND = 1 WHERE CTM_NO =8;
SELECT * FROM CUSTOMER;
SELECT * FROM RENT;
SELECT * FROM MILEAGE;
SELECT SYSDATE FROM DUAL;

INSERT INTO MILEAGE VALUES(12,2,0,4000,'DSDS'); 
UPDATE CUSTOMER SET CTM_MLG=CTM_MLG+4000 WHERE CTM_NO=2;

SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER C NATURAL JOIN RENT R WHERE RENT_DATE =
(SELECT DATE_FORMAT(CURDATE(),'%Y-%M-%D'));
=======

SELECT * FROM CAR;

DELETE FROM MILEAGE WHERE CTM_NO=1;

UPDATE CUSTOMER SET CTM_MLG=CTM_MLG-200  WHERE CTM_NO=1;
