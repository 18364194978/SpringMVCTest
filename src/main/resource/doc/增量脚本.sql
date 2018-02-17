
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
DROP TABLE IF EXISTS `t_user_menu`;
CREATE TABLE `t_user_menu` (
  `user_menu_id` VARCHAR (100) NOT NULL COMMENT '32位GUID',
  `userid` varchar(100) DEFAULT NULL COMMENT '角色id',
  `menu_id` varchar(100) DEFAULT NULL COMMENT '菜单id(t_menu表)',
  PRIMARY KEY (`user_menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;