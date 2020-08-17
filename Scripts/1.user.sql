-- 사용자 계정 생성
CREATE USER RENT_TEST IDENTIFIED BY rootroot;

-- GRANT 명령으로 접속, 사용 권한 주기
GRANT CONNECT, CREATE SESSION, CREATE VIEW, CREATE SYNONYM, RESOURCE TO RENT_TEST;

-- 사용자 확인
SELECT USERNAME, DEFAULT_TABLESPACE, PROFILE, AUTHENTICATION_TYPE
  FROM DBA_USERS 
 WHERE USERNAME = 'RENT_TEST';