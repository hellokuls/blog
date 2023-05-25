## springboot+thymeleaf+mybatis 个人简约博客

> 本博客为第一版本，后续会持续更新！如果你觉得还不错，可以点个star！也可以fork一下一起来玩！

在线演示：http://kuls.one:8080/

## 网站截图
>
![](https://github.com/hellokuls/blog/blob/master/imgs/1.png)

![](https://github.com/hellokuls/blog/blob/master/imgs/2.png)

![](https://github.com/hellokuls/blog/blob/master/imgs/3.png)

![](https://github.com/hellokuls/blog/blob/master/imgs/4.png)
![](https://github.com/hellokuls/blog/blob/master/imgs/5.png)
![](https://github.com/hellokuls/blog/blob/master/imgs/6.png)
![](https://github.com/hellokuls/blog/blob/master/imgs/7.png)
![](https://github.com/hellokuls/blog/blob/master/imgs/8.png)


## 部署
修改mysql、redis配置
```yaml
spring:
  thymeleaf:
    cache: false
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://localhost:3306/springblog?useSSL=true&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    initialSize: 5
    minIdle: 5
    maxActive: 20
    maxWait: 60000
    timeBetweenEvictionRunsMillis: 60000
    minEvictableIdleTimeMillis: 300000
    validationQuery: SELECT 1 FROM DUAL
    testWhileIdle: true
    testOnBorrow: false
    testOnReturn: false
    poolPreparedStatements: true
    filters: stat,wall
    maxPoolPreparedStatementPerConnectionSize: 20
    useGlobalDataSourceStat: true
    connectionProperties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500
  redis:
    host: 127.0.0.1
    port: 6379
    password:
    timeout: 5000
    jedis:
      pool:
        max-active: 20
        max-wait: -1
        min-idle: 0
    database: 0
  servlet:
    multipart:
      max-file-size: 20MB
      max-request-size: 20MB

mybatis:
  type-aliases-package: com.kuls.po
  mapper-locations: classpath:mybatis/mapper/*.xml
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    map-underscore-to-camel-case: true


pagehelper:
  helper-dialect: mysql
  reasonable: true
  support-methods-arguments: true
  params: count=countSql

```

修改相关配置后，直接运行即可

## 技术栈

| 前端技术栈 | 后端技术栈 |
| ---------- | ---------- |
|      semantic-ui      |   springboot         |
|     thymeleaf       |  mybatis          |
|    后台页面部分参考若依        |  redis          |
|      prism      |  druid          |
|      editormd      |  quartz          |

之后会整合shiro做权限

---
若有问题请提issue
