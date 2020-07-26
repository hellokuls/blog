## springboot+thymeleaf+mybatis 个人简约博客

> 本博客为第一版本，后续会持续更新！如果你觉得还不错，可以点个star！


## 网站截图
![](https://imgkr.cn-bj.ufileos.com/43eed587-24e5-4df6-889e-2fa7e66c56a7.png)

![](https://imgkr.cn-bj.ufileos.com/5fc55dfe-e2b9-4d83-bc00-43821f208732.png)

![](https://imgkr.cn-bj.ufileos.com/e61123aa-971a-4968-bb52-ce00180dec0b.png)

![](https://imgkr.cn-bj.ufileos.com/768e2296-1088-4bda-8e98-c72cff9ea681.png)

![](https://imgkr.cn-bj.ufileos.com/cb39f820-941e-4546-a086-e1dd50fc0b20.png)

![](https://imgkr.cn-bj.ufileos.com/b42f131e-454a-4350-9745-8f67943397b9.png)

![](https://imgkr.cn-bj.ufileos.com/5d53a4f7-eed7-4707-9397-04d3168b7dff.png)

![](https://imgkr.cn-bj.ufileos.com/b420ee99-ef17-45ae-84b1-8a0a53a0a0bc.png)

![](https://imgkr.cn-bj.ufileos.com/6dbcfe33-6ae7-486a-a992-c0b34abe8033.png)


![](https://imgkr.cn-bj.ufileos.com/f32af29b-6639-408b-9375-beb02a45c0d5.png)



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

---
若有问题请提issue
