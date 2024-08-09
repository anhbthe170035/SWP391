create database SWP391
use SWP391

create table Users(
username nvarchar(50) not null primary key,
password nvarchar(50) not null,
name nvarchar(50) not null,
gender binary not null,
dob date not null,
img image,
email nvarchar(50) not null,
phone nvarchar(50) not null,
status int not null,
role int not null
)

create table Products(
idpro nvarchar(10) not null primary key,
name nvarchar(255) not null,
brand nvarchar(50) not null,
description nvarchar(1024) not null,
img image
)

create table ProductDetails(
sku nvarchar(25) not null primary key,
pid nvarchar(10) not null,
color nvarchar(50) not null,
cpu nvarchar(50) not null,
cpu_specs nvarchar(50) not null,
ram nvarchar(50) not null,
ram_max nvarchar(50) not null,
gpu nvarchar(50) not null,
gpu2 nvarchar(50),
storage nvarchar(50) not null,
monitor nvarchar(50) not null,
status nvarchar(50) not null,
price int not null,
sale int,
foreign key (pid) references Products(idpro)
)

create table Carts(
id int primary key,
username nvarchar(50) not null,
amount int,
sku nvarchar(25),
foreign key (username) references Users(username),
foreign key (sku) references ProductDetails(sku)
)

create table Invoices(
id int not null primary key,
username nvarchar(50) not null,
fullname nvarchar(50) not null,
email nvarchar(50) not null,
mobile nvarchar(50) not null,
sku nvarchar(25) not null,
price int not null,
foreign key (username) references Users(username),
foreign key (sku) references ProductDetails(sku),
)

create table Categories(
cid int primary key,
cname nvarchar(50)
)

-- Insert Users into the Users table

-- Admins
INSERT INTO Users (username, password, name, gender, dob, img, email, phone, status, role) VALUES
('admin1', 'password123', 'Nguyen Van A', 0x01, '1980-01-01', NULL, 'admin1@example.com', '0901234567', 1, 0),
('admin2', 'password123', 'Tran Thi B', 0x00, '1985-05-15', NULL, 'admin2@example.com', '0907654321', 1, 0);

-- Employees
INSERT INTO Users (username, password, name, gender, dob, img, email, phone, status, role) VALUES
('employee1', 'password123', 'Le Thi C', 0x01, '1990-03-22', NULL, 'employee1@example.com', '0912345678', 1, 1),
('employee2', 'password123', 'Pham Van D', 0x00, '1992-07-30', NULL, 'employee2@example.com', '0918765432', 1, 1),
('employee3', 'password123', 'Nguyen Thi E', 0x01, '1988-11-11', NULL, 'employee3@example.com', '0923456789', 1, 1);

-- Customers
INSERT INTO Users (username, password, name, gender, dob, img, email, phone, status, role) VALUES
('customer1', 'password123', 'Hoang Van F', 0x00, '1995-02-02', NULL, 'customer1@example.com', '0934567890', 1, 2),
('customer2', 'password123', 'Vu Thi G', 0x01, '1994-04-04', NULL, 'customer2@example.com', '0935678901', 1, 2),
('customer3', 'password123', 'Ngo Thi H', 0x01, '1993-06-06', NULL, 'customer3@example.com', '0946789012', 1, 2),
('customer4', 'password123', 'Bui Van I', 0x00, '1991-08-08', NULL, 'customer4@example.com', '0947890123', 1, 2),
('customer5', 'password123', 'Duong Thi J', 0x01, '1992-10-10', NULL, 'customer5@example.com', '0958901234', 1, 2);

-- Insert Products into the Products table
INSERT INTO Products (idpro, name, brand, description, img) VALUES
('LAPTOP001', 'Dell XPS 13', 'Dell', '13.4-inch laptop with 11th Gen Intel Core i7 processor', NULL),
('LAPTOP002', 'MacBook Air M2', 'Apple', '13.6-inch laptop with Apple M2 chip', NULL),
('LAPTOP003', 'HP Spectre x360 14', 'HP', '14-inch 2-in-1 convertible with Intel Core i7', NULL),
('LAPTOP004', 'Lenovo ThinkPad X1 Carbon', 'Lenovo', '14-inch ultrabook with Intel Core i7 and 16GB RAM', NULL),
('LAPTOP005', 'ASUS ROG Zephyrus G14', 'ASUS', '14-inch gaming laptop with AMD Ryzen 9', NULL),
('LAPTOP006', 'Acer Swift 3', 'Acer', '14-inch lightweight laptop with Intel Core i5', NULL),
('LAPTOP007', 'Microsoft Surface Laptop 4', 'Microsoft', '15-inch laptop with Intel Core i7', NULL),
('LAPTOP008', 'MSI GE76 Raider', 'MSI', '17.3-inch gaming laptop with Intel Core i9', NULL),
('LAPTOP009', 'Razer Blade 15', 'Razer', '15.6-inch gaming laptop with NVIDIA RTX 3070', NULL),
('LAPTOP010', 'Huawei MateBook X Pro', 'Huawei', '13.9-inch laptop with Intel Core i7 and 16GB RAM', NULL);

-- Insert ProductDetails into the ProductDetails table
INSERT INTO ProductDetails (sku, pid, color, cpu, cpu_specs, ram, ram_max, gpu, gpu2, storage, monitor, status, price, sale) VALUES
('XPS13-1', 'LAPTOP001', 'Silver', 'Intel Core i7-1185G7', '3.00 GHz, 4 Cores, 8 Threads', '8GB', '16GB', 'Intel Iris Xe', NULL, '512GB SSD', '13.4" FHD', 'New', 1200, 100),
('XPS13-2', 'LAPTOP001', 'Black', 'Intel Core i7-1185G7', '3.00 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '1TB SSD', '13.4" FHD', 'New', 1400, 150),
('MBA-M2-1', 'LAPTOP002', 'Space Gray', 'Apple M2', '3.49 GHz, 8 Cores, 8 Threads', '8GB', '16GB', 'Apple GPU', NULL, '256GB SSD', '13.6" Retina', 'New', 1100, 80),
('MBA-M2-2', 'LAPTOP002', 'Gold', 'Apple M2', '3.49 GHz, 8 Cores, 8 Threads', '16GB', '16GB', 'Apple GPU', NULL, '512GB SSD', '13.6" Retina', 'New', 1400, 120),
('HP360-1', 'LAPTOP003', 'Silver', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '512GB SSD', '14" UHD', 'Used', 1300, 100),
('HP360-2', 'LAPTOP003', 'Black', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '32GB', '32GB', 'Intel Iris Xe', NULL, '1TB SSD', '14" UHD', 'New', 1500, 150),
('X1C-1', 'LAPTOP004', 'Black', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '512GB SSD', '14" WQHD', 'New', 1500, 120),
('X1C-2', 'LAPTOP004', 'Grey', 'Intel Core i7-1165G7', '2.80 GHz, 4 Cores, 8 Threads', '32GB', '32GB', 'Intel Iris Xe', NULL, '1TB SSD', '14" WQHD', 'New', 1800, 170),
('ROG14-1', 'LAPTOP005', 'Gray', 'AMD Ryzen 9 5900HS', '3.30 GHz, 8 Cores, 16 Threads', '16GB', '32GB','AMD Radeon Graphics', 'NVIDIA RTX 3060', '512GB SSD', '14" FHD', 'New', 1400, 120),
('ROG14-2', 'LAPTOP005', 'Black', 'AMD Ryzen 9 5900HS', '3.30 GHz, 8 Cores, 16 Threads', '32GB', '32GB','AMD Radeon Graphics', 'NVIDIA RTX 3070', '1TB SSD', '14" FHD', 'Sold', 1700, 150),
('SWIFT3-1', 'LAPTOP006', 'Silver', 'Intel Core i5-1135G7', '2.40 GHz, 4 Cores, 8 Threads', '8GB', '16GB', 'Intel Iris Xe', NULL, '256GB SSD', '14" FHD', 'Used', 800, 70),
('SWIFT3-2', 'LAPTOP006', 'Gray', 'Intel Core i5-1135G7', '2.40 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '512GB SSD', '14" FHD', 'New', 1000, 100),
('SURFLAP4-1', 'LAPTOP007', 'Platinum', 'Intel Core i7-1185G7', '3.00 GHz, 4 Cores, 8 Threads', '8GB', '32GB', 'Intel Iris Xe', NULL, '512GB SSD', '15" PixelSense', 'New', 1300, 100),
('SURFLAP4-2', 'LAPTOP007', 'Black', 'Intel Core i7-1185G7', '3.00 GHz, 4 Cores, 8 Threads', '16GB', '32GB', 'Intel Iris Xe', NULL, '1TB SSD', '15" PixelSense', 'Sold', 1600, 150),
('GE76-1', 'LAPTOP008', 'Black', 'Intel Core i9-11980HK', '2.60 GHz, 8 Cores, 16 Threads', '32GB', '64GB','Intel UHD Graphics for 11th Gen', 'NVIDIA RTX 3080', '1TB SSD', '17.3" UHD', 'New', 2000, 180),
('GE76-2', 'LAPTOP008', 'Grey', 'Intel Core i9-11980HK', '2.60 GHz, 8 Cores, 16 Threads', '64GB', '64GB','Intel UHD Graphics for 11th Gen', 'NVIDIA RTX 3080', '2TB SSD', '17.3" UHD', 'New', 2500, 250),
('BLADE15-1', 'LAPTOP009', 'Black', 'Intel Core i7-10875H', '2.30 GHz, 8 Cores, 16 Threads', '16GB', '32GB', 'Intel® UHD Graphics for 10th Gen', 'NVIDIA RTX 3070', '512GB SSD', '15.6" QHD', 'New', 1800, 150),
('BLADE15-2', 'LAPTOP009', 'Green', 'Intel Core i7-10875H', '2.30 GHz, 8 Cores, 16 Threads', '32GB', '32GB', 'Intel® UHD Graphics for 10th Gen', 'NVIDIA RTX 3080', '1TB SSD', '15.6" QHD', 'Sold', 2200, 200),
('MATEBOOKX-1', 'LAPTOP010', 'Space Gray', 'Intel Core i7-1135G7', '2.40 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '512GB SSD', '13.9" 3K', 'New', 1400, 120),
('MATEBOOKX-2', 'LAPTOP010', 'Emerald Green', 'Intel Core i7-1135G7', '2.40 GHz, 4 Cores, 8 Threads', '16GB', '16GB', 'Intel Iris Xe', NULL, '1TB SSD', '13.9" 3K', 'New', 1600, 150);

select * from ProductDetails pd 