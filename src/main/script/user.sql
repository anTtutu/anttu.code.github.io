CREATE TABLE `t_user` (
      `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
      `account` varchar(50) NOT NULL COMMENT '账号',
      `password` varchar(50) NOT NULL COMMENT '密码',
      `name` varchar(100) DEFAULT NULL COMMENT '姓名',
      `sex` int(1) DEFAULT 0 COMMENT '性别：0:女，1:男',
      `company` varchar(100) DEFAULT NULL COMMENT '公司',
      `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
      `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';