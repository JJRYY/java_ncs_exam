-- ncs_test_kjr
DROP SCHEMA IF EXISTS ncs_test_kjr;

-- ncs_test_kjr
CREATE SCHEMA ncs_test_kjr;

-- 직책
CREATE TABLE ncs_test_kjr.Title (
	tno   INT         NOT NULL COMMENT '직책코드', -- 직책코드
	tname VARCHAR(20) NULL     COMMENT '직책명' -- 직책명
)
COMMENT '직책';

-- 직책
ALTER TABLE ncs_test_kjr.Title
	ADD CONSTRAINT PK_Title -- 직책 기본키
		PRIMARY KEY (
			tno -- 직책코드
		);