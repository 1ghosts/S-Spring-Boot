-- `s-spring-boot`.t_user_info definition

CREATE TABLE `t_user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int unsigned DEFAULT NULL COMMENT '年龄',
  `alias` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '别名',
  `username` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `mobile` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '号码',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `state` varchar(32) COLLATE utf8mb4_bin NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';