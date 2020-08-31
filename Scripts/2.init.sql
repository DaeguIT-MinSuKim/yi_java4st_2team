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

DROP TABLE MEMO;

/* 고객 */
CREATE TABLE CUSTOMER (
	ctm_no NUMBER(10) NOT NULL, /* 고객번호 */
	ctm_name VARCHAR2(100) NOT NULL, /* 고객명 */
	tel VARCHAR2(13) NOT NULL, /* 연락처 */
	address VARCHAR2(50), /* 주소 */
	ctm_remark VARCHAR2(500), /* 고객비고 */
	ctm_mlg NUMBER, /* 고객마일리지 */
	list_ctm NUMBER(1) /* 블랙리스트고객 */
);

COMMENT ON TABLE CUSTOMER IS '고객';

COMMENT ON COLUMN CUSTOMER.ctm_no IS '고객번호';

COMMENT ON COLUMN CUSTOMER.ctm_name IS '고객명';

COMMENT ON COLUMN CUSTOMER.tel IS '연락처';

COMMENT ON COLUMN CUSTOMER.address IS '주소';

COMMENT ON COLUMN CUSTOMER.ctm_remark IS '고객비고';

COMMENT ON COLUMN CUSTOMER.ctm_mlg IS '고객마일리지';

COMMENT ON COLUMN CUSTOMER.list_ctm IS '블랙리스트고객';

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
	car_remark VARCHAR2(500) /* 차량비고 */
);

COMMENT ON TABLE CAR IS '차량';

COMMENT ON COLUMN CAR.car_no IS '차량번호';

COMMENT ON COLUMN CAR.car_name IS '차량명';

COMMENT ON COLUMN CAR.car_kind IS '차분류';

COMMENT ON COLUMN CAR.fuel IS '연료';

COMMENT ON COLUMN CAR.distance IS '거리';

COMMENT ON COLUMN CAR.fare IS '금액';

COMMENT ON COLUMN CAR.sale IS '할인';

COMMENT ON COLUMN CAR.car_remark IS '차량비고';

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

COMMENT ON TABLE KIND IS '차종';

COMMENT ON COLUMN KIND.car_kind IS '차분류';

COMMENT ON COLUMN KIND.kind_name IS '분류이름';

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
	rent_time NUMBER,/* 대여시간 */
	is_driver NUMBER(1) NOT NULL, /* 기사여부 */
	rent_remark VARCHAR2(500), /* 대여비고 */
	is_rent NUMBER(1) NOT NULL /* 대여여부 */
);

COMMENT ON TABLE RENT IS '대여';

COMMENT ON COLUMN RENT.rent_no IS '대여번호';

COMMENT ON COLUMN RENT.ctm_no IS '고객번호';

COMMENT ON COLUMN RENT.car_no IS '차량번호';

COMMENT ON COLUMN RENT.rent_date IS '대여일자';

COMMENT ON COLUMN RENT.return_date IS '반납일자';

COMMENT ON COLUMN RENT.rent_time IS '대여시간';

COMMENT ON COLUMN RENT.is_driver IS '기사여부';

COMMENT ON COLUMN RENT.rent_remark IS '대여비고';

COMMENT ON COLUMN RENT.is_rent IS '대여여부';

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

CREATE TABLE MEMO (
	YEAR NUMBER(4) NOT NULL,
	MONTH NUMBER(2) NOT NULL,
	DAY NUMBER(2) NOT NULL,
	MEMO VARCHAR2(500) NOT NULL
);
COMMENT ON TABLE MILEAGE IS '마일리지';

COMMENT ON COLUMN MILEAGE.mlg_no IS '마일리지번호';

COMMENT ON COLUMN MILEAGE.ctm_no IS '고객번호';

COMMENT ON COLUMN MILEAGE.mlg_kind IS '마일리지분류';

COMMENT ON COLUMN MILEAGE.mileage IS '마일리지';

COMMENT ON COLUMN MILEAGE.mlg_remark IS '마일리지비고';

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