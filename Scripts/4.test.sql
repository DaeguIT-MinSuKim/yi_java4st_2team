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

SELECT *
  FROM CUSTOMER
 WHERE CTM_NO IN(SELECT CTM_No
				 FROM RENT r LEFT OUTER JOIN CAR c ON r.CAR_NO = c.CAR_NO
				 WHERE c.IS_RENT = 0);
				 
SELECT DISTINCT c.CTM_NO, c.CTM_NAME, c.TEL, c.ADDRESS, (SELECT SUM(MILEAGE) FROM MILEAGE WHERE CTM_NO = 1) AS MILEAGE, c.CTM_REMARK 
FROM CUSTOMER c JOIN MILEAGE m ON c.CTM_NO = m.CTM_NO WHERE c.CTM_NO = 1;

SELECT CTM_NO, CTM_NAME, TEL, ADDRESS, CTM_REMARK, CTM_MLG FROM CUSTOMER WHERE CTM_NO IN(SELECT R.CTM_NO FROM RENT r LEFT OUTER JOIN CUSTOMER c ON r.CTM_NO = c.CTM_NO WHERE IS_RENT=1) ORDER BY CTM_NO;

-----------------------------------------------------------------------------오수정-----------------------------------------------------------------------------

-----------------------------------------------------------------------------김보현-----------------------------------------------------------------------------

-----------------------------------------------------------------------------배성덕-----------------------------------------------------------------------------
