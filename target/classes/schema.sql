DROP TABLE IF EXISTS RetailSales;  
CREATE TABLE RetailSales (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
dealer_code VARCHAR(50) NOT NULL,
month INT(8) NOT NULL,
year INT(8) NOT NULL,
qty INT(8) NOT NULL
);

DROP TABLE IF EXISTS DailySalesRate;  
CREATE TABLE DailySalesRate (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
dealer_code VARCHAR(50) NOT NULL,
month INT(8) NOT NULL,
year INT(8) NOT NULL,
qty INT(8) NOT NULL
);

DROP TABLE IF EXISTS CarlineSales;  
CREATE TABLE CarlineSales (  
id INT AUTO_INCREMENT  PRIMARY KEY,  
carline VARCHAR(50) NOT NULL,
status VARCHAR(50),
month INT(8) NOT NULL,
year INT(8) NOT NULL,
qty INT(8) NOT NULL
);