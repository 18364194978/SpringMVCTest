
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

/*
  添加人：xie
  添加时间：2018-02-17
  说明：添加菜单列表
*/
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menu_id` VARCHAR (20) NOT NULL COMMENT '菜单编号',
  `buttons` varchar(100) DEFAULT NULL COMMENT '按钮',
  `checked` varchar(10) DEFAULT NULL COMMENT '是否选中：0.未选中 1.已选中',
  `expanded` varchar(100) DEFAULT NULL COMMENT '是否展开：0.不展开 1.展开',
  `icon_cls` varchar(10) DEFAULT NULL COMMENT '图标',
  `leaf` varchar(10) DEFAULT NULL COMMENT '是否叶子节点：0.是 1.否',
  `menu_code` varchar(100) DEFAULT NULL COMMENT '菜单编号',
  `menu_name` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `parent_id` varchar(10) DEFAULT NULL COMMENT '父节点id',
  `sort_order` varchar(100) DEFAULT NULL COMMENT '排序编号',
  `url` varchar(100) DEFAULT NULL COMMENT '地址',
  `is_select` varchar(100) DEFAULT NULL COMMENT '',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
  添加人：xie
  添加时间：2018-02-17
  说明：添加权限关联菜单列表
*/
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu` (
  `role_menu_id` VARCHAR (100) NOT NULL COMMENT '32位GUID',
  `role_id` varchar(100) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(100) DEFAULT NULL COMMENT '菜单id(t_menu表)',
  PRIMARY KEY (`role_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*
  添加人：xie
  添加时间：2018-02-24
  说明：添加人员部门列表
*/
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` varchar(100) NOT NULL COMMENT '主键',
  `dept_name` varchar(100) DEFAULT NULL COMMENT '部门名称',
  `company_id` varchar(100) DEFAULT NULL COMMENT '所属单位id',
  `region_id` varchar(100) DEFAULT NULL COMMENT '所属地区id',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
/*
  添加人：xie
  添加时间：2018-02-24
  说明：添加单位列表
*/
DROP TABLE IF EXISTS `t_company`;
CREATE TABLE `t_company` (
  `company_id` varchar(100) NOT NULL COMMENT '主键',
  `company_name` varchar(100) DEFAULT NULL COMMENT '单位名称',
  `region_id` varchar(100) DEFAULT NULL COMMENT '所属地区id',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='单位表';

/*
  添加人：xie
  添加时间：2018-02-24
  说明：添加地区列表
*/
DROP TABLE IF EXISTS `t_region`;
CREATE TABLE `t_region` (
  `region_id` varchar(100) NOT NULL COMMENT '所属地区id',
  `region_name` varchar(100) DEFAULT NULL COMMENT '所属地区名称',
  PRIMARY KEY (`region_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='地区表';

/*
添加人：xie
添加时间：2018-02-24
说明：人员表添加所属部门、所属单位、所属地区列
 */
Alter table t_user Drop column dept_id;
Alter table t_user Add dept_id varchar(100) DEFAULT NULL COMMENT '单位id';
Alter table t_user Drop column company_id;
Alter table t_user Add company_id varchar(100) DEFAULT NULL COMMENT '部门id';
Alter table t_user Drop column region_id;
Alter table t_user Add region_id varchar(100) DEFAULT NULL COMMENT '地区id';
/*
添加人：xie
添加时间：2018-02-24
说明：删除无用列
 */
Alter table t_user Drop column usertype;
/*
  添加人：xie
  添加时间：2018-02-24
  说明：添加角色人员关联表
*/
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_role_id` varchar(100) NOT NULL COMMENT '主键',
  `user_id` varchar(100) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(100) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色人员关联表';
/*
  添加人：xie
  添加时间：2018-02-24
  说明：添加角色表
*/
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` varchar(100) NOT NULL COMMENT '主键',
  `role_name` varchar(100) DEFAULT NULL COMMENT '用户名称',
  `company_id` varchar(100) DEFAULT NULL COMMENT '公司id',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员表';
/*
  添加人：xie
  添加时间：2018-02-25
  说明：插入初始用户表数据
*/
INSERT INTO t_region (region_id, region_name) VALUES ('01', '山东');
INSERT INTO t_company (company_id, company_name,region_id) VALUES ('011', '360','01');
INSERT INTO t_dept (dept_id, dept_name,company_id,region_id) VALUES ('0111', '检测中心','011','01');
INSERT INTO t_role (role_id, role_name,company_id) VALUES ('01111', '管理者','011');
INSERT INTO t_user (userid, username,account,password,sex,dept_count,remarks,enabled,phone,e_mail,dept_id,company_id,region_id)
VALUES ('011111','管理员','admin','1','1','null','null','1','1000','null','0111','011','01');
INSERT INTO t_user_role (user_role_id, user_id,role_id) VALUES ('112', '011111','01111');
INSERT INTO t_role_menu (role_menu_id, role_id,menu_id) VALUES ('211', '01111','10');
INSERT INTO t_menu (menu_id,expanded,leaf,menu_name,sort_order)
 VALUES ('10','false','false','账户管理','1000');
INSERT INTO t_menu (menu_id,expanded,leaf,menu_name,parent_id,sort_order,url)
VALUES ('101','false','true','地区管理','10','1001','umanage.accountManage');