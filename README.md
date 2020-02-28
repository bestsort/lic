## 如何使用

1. 使用 `git clone xxxx`将本项目导入到本地。
2. 将项目作为Maven项目导入到IDE中。
3. 根据`pom.xml`增添数据库用户、数据库以及分配权限，执行`src/main/resources/db.migration/V1__create_all_table.sql`内的sql语句，然后就可以愉快的进行开发了


## 项目模块
```bash
├── common                       # 公共模块
│   ├── config                   # 通用配置
│   ├── utils                    # 通用工具类
|   └── interceptor              # 拦截器
├── file                         # 文件相关模块
├── message                      # 消息模块
├── permissions                  # 权限校验模块
└── user                         # 用户模块
```