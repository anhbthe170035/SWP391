-- Use the SWP391 database
CREATE DATABASE SWP391;
USE SWP391;

-- Create the Categories table
CREATE TABLE Categories (
    cid INT PRIMARY KEY,
    cname NVARCHAR(50) NOT NULL
);

-- Insert sample data into Categories table
INSERT INTO Categories (cid, cname) VALUES
(1, 'Laptops'),
(2, 'Gaming Laptops'),
(3, 'Convertible Laptops');

-- Create the Users table
CREATE TABLE Users (
    username NVARCHAR(50) NOT NULL PRIMARY KEY,
    password NVARCHAR(50) NOT NULL,
    name NVARCHAR(50) NOT NULL,
    gender BIT NOT NULL,
    dob DATE NOT NULL,
    img IMAGE,
    email NVARCHAR(50) NOT NULL,
    phone NVARCHAR(50) NOT NULL,
    status INT NOT NULL,
    role INT NOT NULL
);

-- Create the Products table
CREATE TABLE Products (
    idpro NVARCHAR(10) NOT NULL PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    brand NVARCHAR(50) NOT NULL,
    description NVARCHAR(1024) NOT NULL,
    img IMAGE,
    cid INT,
    FOREIGN KEY (cid) REFERENCES Categories(cid)
);

-- Create the ProductDetails table
CREATE TABLE ProductDetails (
    sku NVARCHAR(25) NOT NULL PRIMARY KEY,
    pid NVARCHAR(10) NOT NULL,
    color NVARCHAR(50) NOT NULL,
    cpu NVARCHAR(50) NOT NULL,
    cpu_specs NVARCHAR(50) NOT NULL,
    ram NVARCHAR(50) NOT NULL,
    ram_max NVARCHAR(50) NOT NULL,
    gpu NVARCHAR(50) NOT NULL,
    gpu2 NVARCHAR(50),
    storage NVARCHAR(50) NOT NULL,
    monitor NVARCHAR(50) NOT NULL,
    status NVARCHAR(50) NOT NULL,
    price INT NOT NULL,
    sale INT,
    FOREIGN KEY (pid) REFERENCES Products(idpro)
);

-- Create the Carts table
CREATE TABLE Carts (
    id INT PRIMARY KEY,
    username NVARCHAR(50) NOT NULL,
    amount INT,
    sku NVARCHAR(25),
    FOREIGN KEY (username) REFERENCES Users(username),
    FOREIGN KEY (sku) REFERENCES ProductDetails(sku)
);

-- Create the Orders table
CREATE TABLE Orders (
    id INT NOT NULL PRIMARY KEY,
    orderdate DATE NOT NULL,
    totalprice INT NOT NULL,
    username NVARCHAR(50) NOT NULL,
    status INT NOT NULL,
    shipdate DATE NULL,
    fromaddress NVARCHAR(250),
    toaddress NVARCHAR(250),
    FOREIGN KEY (username) REFERENCES Users(username)
);

-- Create the OrderDetails table
CREATE TABLE OrderDetails (
    orderid INT NOT NULL,
    sku NVARCHAR(25) NOT NULL,
    quantity INT NOT NULL,
    discount REAL NULL,
    price INT NOT NULL,
    PRIMARY KEY (orderid, sku),
    FOREIGN KEY (orderid) REFERENCES Orders(id),
    FOREIGN KEY (sku) REFERENCES ProductDetails(sku)
);

-- Insert Users into the Users table

-- Admins
INSERT INTO Users (username, password, name, gender, dob, img, email, phone, status, role) VALUES
('admin1', '482c811da5d5b4bc6d497ffa98491e38', 'Nguyen Van A', 1, '1980-01-01', NULL, 'admin1@example.com', '0901234567', 1, 0),
('admin2', '482c811da5d5b4bc6d497ffa98491e38', 'Tran Thi B', 0, '1985-05-15', NULL, 'admin2@example.com', '0907654321', 1, 0);

-- Employees
INSERT INTO Users (username, password, name, gender, dob, img, email, phone, status, role) VALUES
('employee1', '482c811da5d5b4bc6d497ffa98491e38', 'Le Thi C', 1, '1990-03-22', NULL, 'employee1@example.com', '0912345678', 1, 1),
('employee2', '482c811da5d5b4bc6d497ffa98491e38', 'Pham Van D', 0, '1992-07-30', NULL, 'employee2@example.com', '0918765432', 1, 1),
('employee3', '482c811da5d5b4bc6d497ffa98491e38', 'Nguyen Thi E', 1, '1988-11-11', NULL, 'employee3@example.com', '0923456789', 1, 1);

-- Customers
INSERT INTO Users (username, password, name, gender, dob, img, email, phone, status, role) VALUES
('customer1', '482c811da5d5b4bc6d497ffa98491e38', 'Hoang Van F', 0, '1995-02-02', NULL, 'customer1@example.com', '0934567890', 1, 2),
('customer2', '482c811da5d5b4bc6d497ffa98491e38', 'Vu Thi G', 1, '1994-04-04', NULL, 'customer2@example.com', '0935678901', 1, 2),
('customer3', '482c811da5d5b4bc6d497ffa98491e38', 'Ngo Thi H', 1, '1993-06-06', NULL, 'customer3@example.com', '0946789012', 1, 2),
('customer4', '482c811da5d5b4bc6d497ffa98491e38', 'Bui Van I', 0, '1991-08-08', NULL, 'customer4@example.com', '0947890123', 1, 2),
('customer5', '482c811da5d5b4bc6d497ffa98491e38', 'Duong Thi J', 1, '1992-10-10', NULL, 'customer5@example.com', '0958901234', 1, 2);

-- Insert Products into the Products table
INSERT INTO Products (idpro, name, brand, description, img, cid) VALUES
('LAPTOP001', 'Dell XPS 13', 'Dell', '13.4-inch laptop with 11th Gen Intel Core i7 processor', NULL, 1),
('LAPTOP002', 'MacBook Air M2', 'Apple', '13.6-inch laptop with Apple M2 chip', NULL, 1),
('LAPTOP003', 'HP Spectre x360 14', 'HP', '14-inch 2-in-1 convertible with Intel Core i7', NULL, 3),
('LAPTOP004', 'Lenovo ThinkPad X1 Carbon', 'Lenovo', '14-inch ultrabook with Intel Core i7 and 16GB RAM', NULL, 1),
('LAPTOP005', 'ASUS ROG Zephyrus G14', 'ASUS', '14-inch gaming laptop with AMD Ryzen 9', NULL, 2),
('LAPTOP006', 'Acer Swift 3', 'Acer', '14-inch lightweight laptop with Intel Core i5', NULL, 1),
('LAPTOP007', 'Microsoft Surface Laptop 4', 'Microsoft', '15-inch laptop with Intel Core i7', NULL, 1),
('LAPTOP008', 'MSI GE76 Raider', 'MSI', '17.3-inch gaming laptop with Intel Core i9', NULL, 2),
('LAPTOP009', 'Razer Blade 15', 'Razer', '15.6-inch gaming laptop with NVIDIA RTX 3070', NULL, 2),
('LAPTOP010', 'Huawei MateBook X Pro', 'Huawei', '13.9-inch laptop with Intel Core i7 and 16GB RAM', NULL, 1),
('LAPTOP011', 'HP Pavilion x360', 'HP', 'Convertible 2-in-1 laptop with Intel Core i5, 8GB RAM, and 256GB SSD', NULL, 3),
('LAPTOP012', 'Lenovo IdeaPad 3', 'Lenovo', 'Budget laptop with AMD Ryzen 5, 8GB RAM, and 512GB SSD', NULL, 3);

-- Insert ProductDetails into the ProductDetails table
INSERT INTO ProductDetails (sku, pid, color, cpu, cpu_specs, ram, ram_max, gpu, gpu2, storage, monitor, status, price, sale) VALUES
('DLPX13-1', 'LAPTOP001', 'Silver', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '16GB', '32GB', 'Intel Iris Xe', NULL, '512GB SSD', '13.4" FHD', 'New', 1300, 150),
('MBAIR-M2', 'LAPTOP002', 'Space Gray', 'Apple M2', '3.49 GHz, 8 Cores', '8GB', '24GB', 'Apple GPU', NULL, '256GB SSD', '13.6" Retina', 'New', 1300, 150),
('SPX360-1', 'LAPTOP003', 'Nightfall Black', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '1TB SSD', '14" 3K', 'New', 1400, 200),
('TPX1C-1', 'LAPTOP004', 'Black', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '16GB', '32GB', 'Intel Iris Xe', NULL, '512GB SSD', '14" FHD', 'New', 1500, 250),
('ROG14-1', 'LAPTOP005', 'Eclipse Gray', 'AMD Ryzen 9 5900HS', '3.30 GHz, 8 Cores, 16 Threads', '32GB', '32GB', 'NVIDIA RTX 3060', NULL, '1TB SSD', '14" QHD', 'New', 1700, 300),
('SWIFT3-1', 'LAPTOP006', 'Silver', 'Intel Core i5-1135G7', '2.40 GHz, 4 Cores, 8 Threads', '8GB', '8GB', 'Intel Iris Xe', NULL, '256GB SSD', '14" FHD', 'New', 600, 50),
('SFL4-1', 'LAPTOP007', 'Platinum', 'Intel Core i7-1185G7', '3.00 GHz, 4 Cores, 8 Threads', '16GB', '32GB', 'Intel Iris Xe', NULL, '512GB SSD', '15" PixelSense', 'New', 1600, 200),
('GE76-1', 'LAPTOP008', 'Black', 'Intel Core i9-11980HK', '2.60 GHz, 8 Cores, 16 Threads', '32GB', '64GB', 'NVIDIA RTX 3080', NULL, '1TB SSD', '17.3" UHD', 'New', 2500, 400),
('RB15-1', 'LAPTOP009', 'Black', 'Intel Core i7-12800H', '2.40 GHz, 14 Cores, 20 Threads', '16GB', '64GB', 'NVIDIA RTX 3070', NULL, '1TB SSD', '15.6" QHD', 'New', 2200, 350),
('MBXPro-1', 'LAPTOP010', 'Space Gray', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '1TB SSD', '13.9" 3K', 'New', 1400, 200),
('HPX360-1', 'LAPTOP011', 'Silver', 'Intel Core i5-1135G7', '2.40 GHz, 4 Cores, 8 Threads', '8GB', '16GB', 'Intel Iris Xe', NULL, '256GB SSD', '14" FHD', 'New', 600, 50),
('IDP3-1', 'LAPTOP012', 'Abyss Blue', 'AMD Ryzen 5 5500U', '2.10 GHz, 6 Cores, 12 Threads', '8GB', '16GB', 'AMD Radeon Graphics', NULL, '512GB SSD', '15.6" FHD', 'New', 500, 40);
