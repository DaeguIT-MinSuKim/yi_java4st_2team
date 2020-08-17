/* 고객 */
DROP TABLE CUSTOMER 
	CASCADE CONSTRAINTS;

/* 차량 */
DROP TABLE CAR 
	CASCADE CONSTRAINTS;

/* 차분류 */
DROP TABLE KIND 
	CASCADE CONSTRAINTS;

/* 대여 */
DROP TABLE RENT 
	CASCADE CONSTRAINTS;

/* 고객 */
CREATE TABLE CUSTOMER (
	ctm_no NUMBER(10) NOT NULL, /* 고객번호 */
	ctm_name VARCHAR2(100), /* 고객명 */
	tel VARCHAR2(13), /* 연락처 */
	address VARCHAR2(50), /* 주소 */
	ctm_remark VARCHAR2(500), /* 고객비고 */
	mileage NUMBER, /* 마일리지 */
	mlg_remark VARCHAR2(500) /* 마일리지비고 */
);

COMMENT ON TABLE CUSTOMER IS '고객';

COMMENT ON COLUMN CUSTOMER.ctm_no IS '고객번호';

COMMENT ON COLUMN CUSTOMER.ctm_name IS '고객명';

COMMENT ON COLUMN CUSTOMER.tel IS '연락처';

COMMENT ON COLUMN CUSTOMER.address IS '주소';

COMMENT ON COLUMN CUSTOMER.ctm_remark IS '고객비고';

COMMENT ON COLUMN CUSTOMER.mileage IS '마일리지';

COMMENT ON COLUMN CUSTOMER.mlg_remark IS '마일리지비고';

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
	car_no NUMBER(10) NOT NULL, /* 차량번호 */
	car_name VARCHAR2(100), /* 차량명 */
	car_kind VARCHAR2(100), /* 차분류 */
	fuel VARCHAR2(100), /* 연료 */
	distance NUMBER, /* 거리 */
	fare NUMBER, /* 금액 */
	sale NUMBER, /* 할인 */
	car_remark VARCHAR2(500), /* 차량비고 */
	is_rent NUMBER(1) /* 대여여부 */
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

COMMENT ON COLUMN CAR.is_rent IS '대여여부';

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

/* 차분류 */
CREATE TABLE KIND (
	car_kind VARCHAR2(100) NOT NULL, /* 차분류 */
	kind_name VARCHAR2(100) /* 분류이름 */
);

COMMENT ON TABLE KIND IS '차분류';

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
	ctm_no NUMBER(10), /* 고객번호 */
	car_no NUMBER(10), /* 차량번호 */
	rent_date DATE, /* 대여일자 */
	return_date DATE, /* 반납일자 */
	is_driver NUMBER(1), /* 기사여부 */
	rent_remark VARCHAR2(500) /* 대여비고 */
);

COMMENT ON TABLE RENT IS '대여';

COMMENT ON COLUMN RENT.rent_no IS '대여번호';

COMMENT ON COLUMN RENT.ctm_no IS '고객번호';

COMMENT ON COLUMN RENT.car_no IS '차량번호';

COMMENT ON COLUMN RENT.rent_date IS '대여일자';

COMMENT ON COLUMN RENT.return_date IS '반납일자';

COMMENT ON COLUMN RENT.is_driver IS '기사여부';

COMMENT ON COLUMN RENT.rent_remark IS '대여비고';

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