# 使用 openjdk 作为基础镜像
FROM maven:3-jdk-8

# 将当前目录下的所有文件复制到容器的 /app 目录中
COPY . /app

# 设置工作目录为 /app
WORKDIR /app

# 使用 Maven 构建项目并打包成 jar 包
RUN mvn package

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/target/kulsblog.jar"]