CREATE TABLE `user_otp_bind` (
     `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `user_id` bigint(19) DEFAULT NULL COMMENT '用户id',
     `account` varchar(255) DEFAULT NULL COMMENT '用户账号',
     `otp_secret` varchar(255) DEFAULT NULL COMMENT 'otp密钥',
     `is_flag` int(1) DEFAULT 0 COMMENT '是否可用状态 0:可用  1:不可用',
     `del_flag` int(1) DEFAULT 0 COMMENT '是否删除 0:不删除  1:删除',
     `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
     PRIMARY KEY (`id`),
     KEY `idx1` (`user_id`,`account`) COMMENT '用户信息索引',
     UNIQUE KEY `idx2` (`otp_secret`) COMMENT 'opt密钥唯一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户otp鉴权绑定表';