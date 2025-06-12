-- 创建数据库xgdemo
CREATE DATABASE xgdemo;
-- 使用xgdemo数据库
USE xgdemo;
-- 创建user表
CREATE TABLE user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(50) NOT NULL,
                      passwd VARCHAR(50) NOT NULL,
                      age INT,
                      addr VARCHAR(100)
);
-- 插入三条数据
INSERT INTO user (name, passwd, age, addr) VALUES
                                               ('Alice', 'password123', 25, '123 Main St'),
                                               ('Bob', 'password456', 30, '456 Elm St'),
                                               ('Charlie', 'password789', 35, '789 Oak St');
-- 创建数据库xgattack
CREATE DATABASE xgattack;
-- 使用xgattack数据库
USE xgattack;
-- 创建test表
CREATE TABLE test (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      flag VARCHAR(50) NOT NULL
);
-- 插入一条数据
INSERT INTO test (flag) VALUES ('flag{hello_XG}');

