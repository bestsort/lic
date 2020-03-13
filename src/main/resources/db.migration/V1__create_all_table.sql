create table files(
                      id bigint primary key auto_increment,
                      name varchar(50) comment '文件名',
                      is_dir tinyint default 0 comment '文件类型（目录/文件）',
                      parent_path varchar(255) default '/' comment '文件url访问父路径（只记录一级）',
                      real_path varchar(512) comment '文件在磁盘中的真实路径',
                      size bigint default 0 comment '文件大小',
                      unit tinyint default 0 comment '计量单位',
                      md5 char(36) comment 'md5校验和',
                      owner_id bigint comment '所属用户Id',
                      user_read_limit int comment '大于此用户等级的用户可以访问到该文件',
                      user_write_limit int comment '大于此用户等级的用户可以修改该文件',
                      is_removed tinyint default 0 comment '是否删除,1 表示该文件已经被移入回收站',
                      gmt_create datetime DEFAULT CURRENT_TIMESTAMP comment '文件创建时间',
                      gmt_update datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT ' 修改时间 ',
                      KEY `index_owner_id`(`owner_id`),
                      KEY `index_parent_path`(`parent_path`)
)DEFAULT CHARSET=utf8mb4;

create table user(
                     id bigint primary key auto_increment,
                     name char(20) comment '用户名',
                     account char(20) comment '用户帐号',
                     password char(20) comment '用户密码',
                     e_mail char(50) comment '用户邮箱',
                     total_capacity bigint default '10' comment '用户网盘容量,-1表示无穷',
                     used_capacity bigint default '0' comment '用户已使用的容量',
                     capacity_unit char(5) default 'G' comment '容量单位',
                     read_level int comment '用户所属读等级,等级越大权限越大',
                     write_level int comment '用户所属写等级',
                     avatar varchar(255) comment '用户头像地址',
                     UNIQUE KEY `unique_index_account`(`account`),
                     UNIQUE KEY `unique_index_email`(`e_mail`)
)DEFAULT CHARSET=utf8mb4;

create table share(
                      id bigint primary key auto_increment,
                      url char(20) comment '随机一段字符,全路径为host/share/{url}',
                      password char(20) default '' comment '分享密码',
                      end_time datetime comment '文件分享过期时间,若结束时间==开始时间则为永久',
                      begin_time datetime comment '文件分享开始时间'
)DEFAULT CHARSET=utf8mb4;

create table share_map(
                          id bigint primary key auto_increment,
                          share_id bigint not null ,
                          file_id bigint not null
)DEFAULT CHARSET=utf8mb4 comment '分享-文件关系表';

create table message(
                        id bigint primary key auto_increment,
                        send_form_id bigint default 0 comment '发送人id,若为0则此条消息为系统消息',
                        send_to_id bigint not null comment '接受人id',
                        content varchar(1024) not null comment '消息具体内容',
                        gmt_create datetime default CURRENT_TIMESTAMP comment '消息发送时间',
                        is_read tinyint default 0 comment '已阅标记'
)DEFAULT CHARSET=utf8mb4 comment '消息表';


create table options(
                        id bigint primary key auto_increment,
                        option_key varchar(255) not null ,
                        option_value varchar(255) not null ,
                        user_id bigint default 0 comment '配置所对应的用户id,为0则表示为系统配置',
                        gmt_create datetime default  CURRENT_TIMESTAMP comment '创建时间',
                        gmt_update datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
                        KEY `index_user_id`(`user_id`),
                        KEY `index_option_key`(`option_key`)
)DEFAULT CHARSET=utf8mb4 comment '用户配置表';