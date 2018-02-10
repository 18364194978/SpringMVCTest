
/*
  添加人：xie
  添加时间：2018-02-10
  说明：添加测试表
*/
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
  添加人：xie
  添加时间：2018-02-10
  说明：添加用户表
*/
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userid` VARCHAR (100) NOT NULL COMMENT '用户编号，32位GUID',
  `username` varchar(100) DEFAULT NULL COMMENT '用户名',
  `account` varchar(100) DEFAULT NULL COMMENT '登录账户',
  `password` varchar(100) DEFAULT NULL COMMENT '登录密码',
  `sex` varchar(10) DEFAULT NULL COMMENT '性别：1.男 2.女',
  `dept_count` varchar(100) DEFAULT NULL COMMENT '部门编号',
  `remarks` varchar(500) DEFAULT NULL COMMENT '备注',
  `usertype` varchar(10) DEFAULT NULL COMMENT '人员类型：0.超级管理员 1.管理员 2.普通用户',
  `enabled` varchar(10) DEFAULT NULL COMMENT '启用状态 0.未启用 1.已启用',
  `phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `e_mail` varchar(100) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;