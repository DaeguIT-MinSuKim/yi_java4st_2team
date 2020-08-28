-----------------------------------------------------------------------------김창동-----------------------------------------------------------------------------
SELECT * FROM USER_TABLES ;

SELECT * FROM RENT;
SELECT * FROM CUSTOMER;
SELECT * FROM CAR;
SELECT * FROM KIND;
SELECT * FROM MILEAGE;

SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER;

SELECT DISTINCT c.CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = c.CTM_NO ) AS MILEAGE
FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO;

				 
SELECT DISTINCT c.CTM_NO, c.CTM_NAME, c.TEL, c.ADDRESS, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = 1) AS MILEAGE, c.CTM_REMARK 
FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO WHERE c.CTM_NO = 1;


SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER WHERE CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) ORDER BY CTM_NO; 

SELECT MLG_NO, CTM_NO, MLG_KIND, MILEAGE, MLG_REMARK FROM MILEAGE;
SELECT MLG_NO, CTM_NO, MLG_KIND, MILEAGE, MLG_REMARK 
  FROM MILEAGE 
 WHERE MLG_NO = 1; 

SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER WHERE CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) ORDER BY CTM_NO;

-----------------------------------------------------------------------------오수정-----------------------------------------------------------------------------
SELECT r.RENT_NO, car.CAR_NAME, car.CAR_NO, c.CTM_NO , c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK 
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO ;
 
SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK 
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO ;
 
SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE r.RENT_DATE >= SYSDATE - 5 AND r.RETURN_DATE <= SYSDATE + 2;
 
SELECT car.CAR_NAME, car.CAR_NO, c.CTM_NAME , c.TEL, r.RENT_DATE, r.RETURN_DATE, r.RENT_TIME, r.IS_DRIVER, r.RENT_REMARK
  FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO JOIN CAR car ON r.Car_NO = car.CAR_NO
 WHERE c.CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1)
 ORDER BY r.RENT_NO;

INSERT INTO RENT values(7, 6, '06호8254', '2020/03/30', '2020/05/22', 1, 0, '차량파손',0);

UPDATE RENT 
   SET CTM_NO = 3, CAR_NO = '12하4219', RENT_DATE = '2020-08-25', RETURN_DATE = '2020-08-28', RENT_TIME = ((SYSDATE + 1) - SYSDATE) * 24, IS_DRIVER = 1, RENT_REMARK = '아아아아아악'
 WHERE RENT_NO = 7;

DELETE FROM RENT WHERE RENT_NO = 7;
 
SELECT *
  FROM RENT;

-----------------------------------------------------------------------------김보현-----------------------------------------------------------------------------
SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER WHERE LIST_CTM = 1;
UPDATE CAR SET CAR_NAME = '스타' , CAR_KIND = 'S' , FUEL = '휘발유' , DISTANCE = 999 , FARE = 350000 , SALE = 5 , CAR_REMARK = '티라노' WHERE CAR_NO = '38허4532';
-----------------------------------------------------------------------------배성덕-----------------------------------------------------------------------------
