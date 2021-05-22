DROP SCHEMA IF EXISTS `salesManager`;
CREATE SCHEMA IF NOT EXISTS `salesManager`;
USE `salesManager`;


-- THÔNG TIN TÀI KHOẢN
DROP TABLE IF EXISTS `USER`;
CREATE TABLE IF NOT EXISTS `USER` (
	`USER_ID` BIGINT AUTO_INCREMENT NOT NULL,
	`LAST_NAME` NVARCHAR(40) NOT NULL,
	`FIRST_NAME` NVARCHAR(10) NOT NULL,
	`GENDER` NVARCHAR(10) NULL DEFAULT 'Khác',						-- NAM, NỮ, KHÁC
	`DATE_OF_BIRTH` NVARCHAR(30) NULL,										-- NGÀY SINH
    
    `PHONE_NUMBER` VARCHAR(15) NULL,
    `EMAIL` VARCHAR(40) NULL,
    `IMAGE` LONGTEXT DEFAULT NULL ,									-- BASE64 IMAGE
    
	`USER_NAME` VARCHAR(40) NOT NULL,
	`PASSWORD` VARCHAR(32) NOT NULL,
    `USER_TYPE` VARCHAR(15) DEFAULT 'CUSTOMER' NOT NULL,			-- ADMIN, EMPLOYEE, CUSTOMER
    
	`STATUS` TINYINT(1) DEFAULT '1',								-- TRẠNG THÁI
    
    PRIMARY KEY(`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- THƯƠNG HIỆU
DROP TABLE IF EXISTS `BRAND`;
CREATE TABLE IF NOT EXISTS `BRAND` (
	`BRAND_ID` BIGINT AUTO_INCREMENT,
	`BRAND_NAME` NVARCHAR(50) NOT NULL,
	`BRAND_ORIGIN` NVARCHAR(30) NOT NULL,				-- XUẤT XỨ THƯƠNG HIỆU
   `IMAGE` LONGTEXT DEFAULT NULL,
    
    PRIMARY KEY(`BRAND_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=UTF8MB4_UNICODE_CI;

-- THỂ LOẠI
DROP TABLE IF EXISTS `CATEGORY`;
CREATE TABLE IF NOT EXISTS `CATEGORY` (
	`CATEGORY_ID` BIGINT AUTO_INCREMENT,
	`CATEGORY_TITLE` NVARCHAR(50) NOT NULL,
	`IMAGE` LONGTEXT DEFAULT NULL,				
   `STATUS` TINYINT(1) DEFAULT '1',
    
    PRIMARY KEY(`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=UTF8MB4_UNICODE_CI;

-- NGƯỜI BÁN HÀNG
DROP TABLE IF EXISTS `SELLER`;
CREATE TABLE IF NOT EXISTS `SELLER` (
	`USER_ID` BIGINT NOT NULL,
	`STORE_NAME` NVARCHAR(50),					-- TÊN SHOP
	`STORE_LINK` VARCHAR(50),					
	`BUSINESS_LICENSE_ID` VARCHAR(50),			-- GIẤY PHÉP KINH DOANH
	`BANK_ACCOUNT_ID` BIGINT,					-- SỐ TÀI KHOẢN
    
    PRIMARY KEY(`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
ALTER TABLE `SELLER` ADD CONSTRAINT `FK_SELLER_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `USER`(`USER_ID`);

-- SẢN PHẨM
DROP TABLE IF EXISTS `PRODUCT`;
CREATE TABLE IF NOT EXISTS `PRODUCT` (
	`PRODUCT_ID` BIGINT AUTO_INCREMENT,	
    
	`BRAND_ID` BIGINT NOT NULL,							-- THƯƠNG HIỆU
	`SELLER_ID` BIGINT NOT NULL,						-- SELLER.USER_ID: ID NGƯỜI BÁN HÀNG
	
	`PRODUCT_NAME` NVARCHAR(100) NOT NULL,
	`PRODUCT_RATE` FLOAT DEFAULT 0 NULL,
	`PRODUCT_ORIGIN` NVARCHAR(30) NOT NULL, 			-- XUẤT XỨ SẢN PHẨM
	`PRODUCT_DESC` LONGTEXT NOT NULL,					-- MÔ TẢ SẢN PHẨM
	
	-- `QUANTITY` INT NOT NULL,							-- SỐ LƯỢNG SẢN PHẨM 
	`PRICE_ORIGIN` DECIMAL(19, 2) NOT NULL,				-- GIÁ MẶC ĐỊNH
	`PRICE_ORDER` DECIMAL(19, 2) NOT NULL,			-- NGÀY ĐĂNG BÁN
    
    `IMAGE_0` LONGTEXT DEFAULT NULL,
    `IMAGE_1` LONGTEXT DEFAULT NULL,
    `IMAGE_2` LONGTEXT DEFAULT NULL,
    `IMAGE_3` LONGTEXT DEFAULT NULL,
    `IMAGE_4` LONGTEXT DEFAULT NULL,
    
    `STATUS` INT NOT NULL DEFAULT 0,
    
    PRIMARY KEY(`PRODUCT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
ALTER TABLE `PRODUCT` ADD CONSTRAINT `FK_BRAND_ID` FOREIGN KEY (`BRAND_ID`) REFERENCES `BRAND`(`BRAND_ID`);
ALTER TABLE `PRODUCT` ADD CONSTRAINT `FK_SELLER_ID` FOREIGN KEY (`SELLER_ID`) REFERENCES `SELLER`(`USER_ID`);