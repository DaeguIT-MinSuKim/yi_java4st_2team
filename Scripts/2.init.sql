/* 고객 */
DROP TABLE CUSTOMER 
	CASCADE CONSTRAINTS;

/* 차량 */
DROP TABLE CAR 
	CASCADE CONSTRAINTS;

/* 차종 */
DROP TABLE KIND 
	CASCADE CONSTRAINTS;

/* 대여 */
DROP TABLE RENT 
	CASCADE CONSTRAINTS;

/* 마일리지 */
DROP TABLE MILEAGE 
	CASCADE CONSTRAINTS;

/* 고객 */
CREATE TABLE CUSTOMER (
	ctm_no NUMBER(10) NOT NULL, /* 고객번호 */
	ctm_name VARCHAR2(100) NOT NULL, /* 고객명 */
	tel VARCHAR2(13) NOT NULL, /* 연락처 */
	address VARCHAR2(50), /* 주소 */
	ctm_remark VARCHAR2(500) /* 고객비고 */
);

CREATE UNIQUE INDEX PK_CUSTOMER
	ON CUSTOMER (
		ctm_no ASC
	);

ALTER TABLE CUSTOMER
	ADD
		CONSTRAINT PK_CUSTOMER
		PRIMARY KEY (
			ctm_no
		);

/* 차량 */
CREATE TABLE CAR (
	car_no VARCHAR2(20) NOT NULL, /* 차량번호 */
	car_name VARCHAR2(100) NOT NULL, /* 차량명 */
	car_kind VARCHAR2(100) NOT NULL, /* 차분류 */
	fuel VARCHAR2(100) NOT NULL, /* 연료 */
	distance NUMBER, /* 거리 */
	fare NUMBER NOT NULL, /* 금액 */
	sale NUMBER, /* 할인 */
	car_remark VARCHAR2(500), /* 차량비고 */
	is_rent NUMBER(1) NOT NULL /* 대여여부 */
);

CREATE UNIQUE INDEX PK_CAR
	ON CAR (
		car_no ASC
	);

ALTER TABLE CAR
	ADD
		CONSTRAINT PK_CAR
		PRIMARY KEY (
			car_no
		);

/* 차종 */
CREATE TABLE KIND (
	car_kind VARCHAR2(100) NOT NULL, /* 차분류 */
	kind_name VARCHAR2(100) NOT NULL /* 분류이름 */
);

CREATE UNIQUE INDEX PK_KIND
	ON KIND (
		car_kind ASC
	);

ALTER TABLE KIND
	ADD
		CONSTRAINT PK_KIND
		PRIMARY KEY (
			car_kind
		);

/* 대여 */
CREATE TABLE RENT (
	rent_no NUMBER(10) NOT NULL, /* 대여번호 */
	ctm_no NUMBER(10) NOT NULL, /* 고객번호 */
	car_no VARCHAR2(20) NOT NULL, /* 차량번호 */
	rent_date DATE NOT NULL, /* 대여일자 */
	return_date DATE NOT NULL, /* 반납일자 */
	rent_time NUMBER, /* 대여 */
	is_driver NUMBER(1) NOT NULL, /* 기사여부 */
	rent_remark VARCHAR2(500) /* 대여비고 */
);

CREATE UNIQUE INDEX PK_RENT
	ON RENT (
		rent_no ASC
	);

ALTER TABLE RENT
	ADD
		CONSTRAINT PK_RENT
		PRIMARY KEY (
			rent_no
		);


/* 마일리지 */
CREATE TABLE MILEAGE (
	mlg_no NUMBER(10) NOT NULL, /* 마일리지번호 */
	ctm_no NUMBER(10) NOT NULL, /* 고객번호 */
	mlg_kind NUMBER(1) NOT NULL, /* 마일리지분류 */
	mileage NUMBER, /* 마일리지 */
	mlg_remark VARCHAR2(500) /* 마일리지비고 */
);

CREATE UNIQUE INDEX PK_MILEAGE
	ON MILEAGE (
		mlg_no ASC
	);

ALTER TABLE MILEAGE
	ADD
		CONSTRAINT PK_MILEAGE
		PRIMARY KEY (
			mlg_no
		);

ALTER TABLE CAR
	ADD
		CONSTRAINT FK_KIND_TO_CAR
		FOREIGN KEY (
			car_kind
		)
		REFERENCES KIND (
			car_kind
		);

ALTER TABLE RENT
	ADD
		CONSTRAINT FK_CUSTOMER_TO_RENT
		FOREIGN KEY (
			ctm_no
		)
		REFERENCES CUSTOMER (
			ctm_no
		);

ALTER TABLE RENT
	ADD
		CONSTRAINT FK_CAR_TO_RENT
		FOREIGN KEY (
			car_no
		)
		REFERENCES CAR (
			car_no
		);

ALTER TABLE MILEAGE
	ADD
		CONSTRAINT FK_CUSTOMER_TO_MILEAGE
		FOREIGN KEY (
			ctm_no
		)
		REFERENCES CUSTOMER (
			ctm_no
		);