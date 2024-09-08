---
### 👉作者QQ ：1556708905 微信：zheng0123Long (支持修改、部署调试、定制毕设)

### 👉接网站建设、小程序、H5、APP、各种系统等

### 👉选题+开题报告+任务书+程序定制+安装调试+ppt 都可以做
---

**毕业设计所有选题地址 [https://github.com/zhengjianzhong0107/allProject](https://github.com/zhengjianzhong0107/allProject)**

**博客地址：
[https://blog.csdn.net/2303_76227485/article/details/131913416](https://blog.csdn.net/2303_76227485/article/details/131913416)**

**视频演示：
[https://www.bilibili.com/video/BV1xM4y1p7f2/](https://www.bilibili.com/video/BV1xM4y1p7f2/)**

   

## 基于Springboot+Vue的医院hrp物资管理系统(源代码+数据库+16000字论文)066

## 一、系统介绍

本系统分为管理员、医院、供应商三种角色

供应商角色包含以下功能：

- 注册登录、个人中心、招标信息管理、合同签订管理、产品信息管理、采购订单管理、订单配送管理、条码维护管理、查看招标公告、密码修改

医院角色包含以下功能：

- 注册登录、个人中心、密码修改、招标公告管理、招标信息管理、专家抽取管理、结果公示管理、合同签订管理、产品信息管理、采购订单管理
- 订单配送管理、条码维护管理、物资信息管理、物资入库管理、物资出库管理、设备资产管理、设备折旧管理、资产入库管理
- 资产报废管理、预备账户管理。

管理员角色包含以下功能：

- 包括医院角色的所有功能、供应商管理、医院管理、系统管理

## 二、所用技术

后端技术栈：

- Springboot
- MybatisPlus
- Mysql

前端技术栈：

- Vue 
- Vue-router 
- axios 
- Ajax 
- element-ui

## 三、环境介绍

基础环境 :IDEA/eclipse, JDK1.8, Mysql5.7及以上,Maven3.6, node14

所有项目以及源代码本人均调试运行无问题 可支持远程调试运行

## 四、页面截图

论文目录
![contents](./picture/picture0.png)

### 1、前台页面

![contents](./picture/picture1.png)
![contents](./picture/picture2.png)
![contents](./picture/picture3.png)
![contents](./picture/picture4.png)
![contents](./picture/picture5.png)
![contents](./picture/picture6.png)
![contents](./picture/picture7.png)

### 2、供应商后台页面

![contents](./picture/picture8.png)
![contents](./picture/picture9.png)
![contents](./picture/picture10.png)
![contents](./picture/picture11.png)
![contents](./picture/picture12.png)
![contents](./picture/picture13.png)
![contents](./picture/picture14.png)
![contents](./picture/picture15.png)
![contents](./picture/picture16.png)
![contents](./picture/picture17.png)

### 3、医院后台页面

![contents](./picture/picture18.png)
![contents](./picture/picture19.png)
![contents](./picture/picture20.png)
![contents](./picture/picture21.png)
![contents](./picture/picture22.png)
![contents](./picture/picture23.png)
![contents](./picture/picture24.png)
![contents](./picture/picture25.png)
![contents](./picture/picture26.png)
![contents](./picture/picture27.png)
![contents](./picture/picture28.png)
![contents](./picture/picture29.png)
![contents](./picture/picture30.png)
![contents](./picture/picture31.png)
![contents](./picture/picture32.png)
![contents](./picture/picture33.png)
![contents](./picture/picture34.png)
![contents](./picture/picture35.png)
![contents](./picture/picture36.png)
![contents](./picture/picture37.png)
![contents](./picture/picture38.png)
![contents](./picture/picture39.png)

### 4、管理员后台页面

![contents](./picture/picture40.png)
![contents](./picture/picture41.png)

## 五、浏览地址

访问地址以vue启动后的地址为准

前台访问地址：http://localhost:8082/

- 供应商账号/密码：供应商账号1/123456

后台访问地址：http://localhost:8081/

- 管理员账号/密码：admin/admin
- 供应商账号/密码：供应商账号1/123456
- 医院账号/密码：医院账号1/123456
  
  ## 六、部署教程
1. 使用Navicat或者其它工具，在mysql中创建对应名称的数据库，并执行项目的sql文件；
2. 使用IDEA/Eclipse导入项目，若为maven项目请选择maven，等待依赖下载完成；
3. 进入src/main/resources修改application.yml 里面的数据库配置
4. 启动项目后端项目
5. vscode或idea打开src/main/resources/admin/admin项目，
6. 在编译器中打开terminal，执行npm install 依赖下载完成后执行 npm run serve,执行成功后会显示访问地址
7. src/main/resources/front/front项目执行5、6步骤

 
