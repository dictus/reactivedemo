-- Create Users Table
CREATE TABLE if not exists users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(100) NOT NULL
);

-- Create Medicines Table
CREATE TABLE if not exists medicines (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    max_quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Create Orders Table
CREATE TABLE  if not exists orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    medicine_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (medicine_id) REFERENCES medicines(id) ON DELETE CASCADE
);


--INSERT into medicines values (1,'te',3,100);
--INSERT into medicines values (2,'te3',4,200);

CREATE TABLE if not exists event (
    id BIGINT PRIMARY KEY,
    name VARCHAR(100) NOT NULL ,
    location VARCHAR(100) NOT  NULL,
    cost BIGINT NOT NULL,
    duration BIGINT NOT NULL
);

CREATE TABLE if not exists EMPLOYEES (
    EMP_ID        INT   PRIMARY KEY,
    EMP_NAME      VARCHAR(100) NOT NULL,
    DEPARTMENT    VARCHAR(50),
    UPDATED_AT    TIMESTAMP     DEFAULT CURRENT_TIMESTAMP
);