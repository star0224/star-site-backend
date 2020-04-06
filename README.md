# Star Site Backend

## 1. 概述
项目整体采用前后端分离模式，此项目为`后端`部分。

项目地址：http://star0224.com

## 2. 部署
后端部署可以采用如下两种方式：

### 2.1 从 DockerHub 上获取镜像并运行
该部署方法适合只查看运行效果而不修改源代码。

#### 2.1.1 从 DockerHub 上获取镜像
可以通过如下`Docker`的`pull`命令来拉去镜像，当前最新版本为`1.5.6`，具体命令如下所示：

```shell script
docker pull 1048950702/star-site-backend:1.5.6
```

#### 2.1.2 将获取的镜像以容器方式运行
可以通过如下命令将上一步获取的镜像以容器方式运行：

```shell script
docker run -dit -p 8080:8080 --restart=always 1048950702/star-site-backend:1.5.6 
```

### 2.2 使用Maven打包后制成Docker镜像并运行
该方法为修改源代码后部署流程。

### 2.1 Maven打包
通过`maven`打包生成一个`jar`包，具体命令如下：
  
```shell script
mvn clean package
```

命令执行完后在`/target`目录会生成`star-site-backend-version`的`jar`包，其中`version`为当前`POM.xml`所指定的版本号。
   
### 2.2 Docker生成镜像

该镜像将Java环境也打包进该项目。`Dockerfile`已上传，直接在项目根目录下调用以下命令即可：

```shell script
docker build -t 1048950702/star-site-backend:version . 
```

其中：
- `1048950702`是我的`DockerHub`用户名，需要替换为你自己的用户名。

- `version`是广义上的版本号，可以自己指定。特别地，不加版本号会生成一个latest版本的镜像。
   
### 2.3 Docker运行容器
可以通过如下命令将上一步获取的镜像以容器方式运行：

```shell script
docker run -dit -p 8080:8080 --restart=always 1048950702/star-site-backend:version 
```



## 3. 技术选型
- 基础：SpringBoot
- REST： SpringMVC
- 持久层：JPA
- JSON转换：FastJson
- 数据库：MySQL
- 缓存：Redis
- 包管理：Maven
- 部署：Docker
- 版本控制：Git
- CI/CD：TeamCity
