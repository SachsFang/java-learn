# 数据库
drop database if exists springboot_learn;
create database springboot_learn;
# 使用该数据库
use springboot_learn;
# 用户表
drop table if exists `users`;
create table `users`
(
    id   int(11) primary key auto_increment,
    name varchar(64),
    age  int(3)
);
