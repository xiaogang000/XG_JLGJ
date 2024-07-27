-- 创建数据库sqldemo
CREATE DATABASE sqldemo;
-- 使用sqldemo数据库
USE sqldemo;
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
-- 创建数据库sqldemo2
CREATE DATABASE sqldemo2;
-- 使用sqldemo2数据库
USE sqldemo2;
-- 创建test表
CREATE TABLE test (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      flag VARCHAR(50) NOT NULL
);
-- 插入一条数据
INSERT INTO test (flag) VALUES ('flag{hello_XG}');

