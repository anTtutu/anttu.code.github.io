CREATE TABLE `m_app` (
     `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `app_name` varchar(255) DEFAULT NULL COMMENT 'app应用名称',
     `app_id` varchar(255) DEFAULT NULL COMMENT 'app应用系统分配的id, 全局唯一，不可更改',
     `app_secret` varchar(255) DEFAULT NULL COMMENT 'app应用系统分配的密钥, 忘记了可刷新重新生成',
     `is_flag` int(1) DEFAULT 0 COMMENT '是否可用状态 0:可用  1:不可用',
     `access_token` varchar(1000) DEFAULT NULL COMMENT 'app应用上一次可用的令牌token',
     `del_flag` int(1) DEFAULT 0 COMMENT '是否删除 0:不删除  1:删除',
     `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
     `update_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp() COMMENT '修改时间',
     PRIMARY KEY (`id`),
     KEY `idx1` (`app_id`,`app_secret`) COMMENT 'appId索引',
     UNIQUE KEY `idx2` (`app_name`) COMMENT 'appName索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='app应用api鉴权表设计';