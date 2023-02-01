# 数据库
drop database if exists springboot_learn_data;
create database springboot_learn_data;
# 使用该数据库
use springboot_learn_data;
# 用户表
drop table if exists `order`;
create table `order`
(
    id   int(11) primary key auto_increment,
    code varchar(64),
    productId varchar(64),
    amount int(11),
    remark  varchar(512)
);
