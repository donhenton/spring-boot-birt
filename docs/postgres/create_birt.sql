
DROP TABLE IF EXISTS  Customers;
DROP TABLE IF EXISTS  Employees;
DROP TABLE IF EXISTS  Offices;
DROP TABLE IF EXISTS  OrderDetails;
DROP TABLE IF EXISTS  Orders;
DROP TABLE IF EXISTS  Payments;
DROP TABLE IF EXISTS  Products;

CREATE TABLE Customers (
  customerNumber SERIAL PRIMARY KEY,
  customerName CHAR(50) NOT NULL,
  contactLastName CHAR(50) NOT NULL,
  contactFirstName CHAR(50) NOT NULL,
  phone CHAR(50) NOT NULL,
  addressLine1 CHAR(50) NOT NULL,
  addressLine2 CHAR(50) NULL,
  city CHAR(50) NOT NULL,
  state CHAR(50) NULL,
  postalCode CHAR(15) NULL,
  country CHAR(50) NOT NULL,
  salesRepEmployeeNumber INT NULL,
  creditLimit DECIMAL NULL 
);


CREATE TABLE Employees (
  employeeNumber SERIAL PRIMARY KEY,
  lastName CHAR(50) NOT NULL,
  firstName CHAR(50) NOT NULL,
  extension CHAR(10) NOT NULL,
  email CHAR(100) NOT NULL,
  officeCode CHAR(20) NOT NULL,
  reportsTo INT NULL,
  jobTitle CHAR(50) NOT NULL  
);

CREATE TABLE Offices (
  officeCode CHAR(50) NOT NULL,
  city CHAR(50) NOT NULL,
  phone CHAR(50) NOT NULL,
  addressLine1 CHAR(50) NOT NULL,
  addressLine2 CHAR(50) NULL,
  state CHAR(50) NULL,
  country CHAR(50) NOT NULL,
  postalCode CHAR(10) NOT NULL,
  territory CHAR(10) NOT NULL,
  PRIMARY KEY (officeCode)
);

CREATE TABLE OrderDetails (
  orderNumber INT NOT NULL,
  productCode CHAR(50) NOT NULL,
  quantityOrdered INT NOT NULL,
  priceEach DECIMAL NOT NULL,
  orderLineNumber SMALLINT NOT NULL,
  PRIMARY KEY (orderNumber, productCode)
);

CREATE TABLE Orders (
  orderNumber SERIAL PRIMARY KEY,
  orderDate timestamp NOT NULL,
  requiredDate timestamp NOT NULL,
  shippedDate timestamp NULL,
  status CHAR(15) NOT NULL,
  comments TEXT NULL,
  customerNumber INT NOT NULL 
);

CREATE TABLE Payments (
  customerNumber INT NOT NULL,
  checkNumber CHAR(50) NOT NULL,
  paymentDate timestamp NOT NULL,
  amount DECIMAL NOT NULL,
  PRIMARY KEY (customerNumber, checkNumber)
);

CREATE TABLE Products (
  productCode CHAR(50) NOT NULL,
  productName CHAR(70) NOT NULL,
  productLine CHAR(50) NOT NULL,
  productScale CHAR(10) NOT NULL,
  productVendor CHAR(50) NOT NULL,
  productDescription TEXT NOT NULL,
  quantityInStock SMALLINT NOT NULL,
  buyPrice DECIMAL NOT NULL,
  MSRP DECIMAL NOT NULL,
  PRIMARY KEY (productCode)
);