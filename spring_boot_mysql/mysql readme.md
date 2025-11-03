# 这是一个关于利用spring_boot来连接mysql的项目
### 1.properties配置


```properties
# 配置Spring Boot应用名称（用于标识应用，在日志、注册中心等场景使用）
spring.application.name=spring_boot_mysql

# 配置Web服务器端口号（应用启动后通过 http://localhost:8080 访问）
server.port=8080

# JPA相关配置（基于Hibernate实现）
# 开启SQL语句打印（控制台输出执行的SQL，方便调试）
spring.jpa.show-sql=true
# 格式化SQL语句（换行缩进，增强可读性）
spring.jpa.properties.hibernate.format_sql=true
# Hibernate自动建表策略：update（根据实体类自动更新表结构，不删除已有数据）
spring.jpa.hibernate.ddl-auto=update

# 数据库连接配置（MySQL）
# 连接URL：指定数据库地址、端口、库名及连接参数
# - localhost:3306：数据库主机和端口
# - spring_boot：目标数据库名称
# - 编码设置：UTF-8避免中文乱码
# - useSSL=false：关闭SSL（开发环境常用）
# - 时区：设置为上海时区，避免时间偏差
spring.datasource.url=jdbc:mysql://localhost:3306/spring_boot?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
# 数据库登录用户名
spring.datasource.username=cccc
# 数据库登录密码
spring.datasource.password=123456
# MySQL JDBC驱动类（8.0+版本使用此类，需配合mysql-connector-java依赖）
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

### 2.定义pojo类

```java
package com.chen.spring_boot_mysql.pojo;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Table(name="table_user")
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_Id")
    private  Integer id;
    @Column(name="User_Name")
    @NotBlank(message = "用户名不能为空")
    private  String username;
    @Column(name="User_Password")
    @NotBlank(message = "密码不能为空")
    private  String password;
    @Column(name="User_Email")
    @NotBlank(message = "邮箱不能为空")
    private  String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```
#### 一、核心注解详解（ORM映射 + 数据校验）
##### 1. ORM映射注解（JPA）
这些注解的作用是告诉框架：“这个类对应数据库中的哪张表？哪个字段对应表中的哪一列？主键如何生成？”

| 注解 | 位置 | 作用 | 细节 |
|------|------|------|------|
| `@Entity` | 类级别 | 标记当前类为JPA实体类，说明它需要与数据库表关联 | 框架会自动扫描带有该注解的类，进行数据库映射处理 |
| `@Table(name = "table_user")` | 类级别 | 指定实体类对应的数据库表名 | 若不指定，默认表名与类名（`Users`）一致；这里显式指定为`table_user`（修正了之前的空格问题，符合数据库命名规范） |
| `@Id` | 字段级别（`id`字段） | 标记该字段为数据库表的**主键（Primary Key）** | 每个实体类必须有且仅有一个主键字段，用于唯一标识一条记录 |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | 主键字段（`id`） | 指定主键的生成策略 | `IDENTITY`表示依赖数据库的自增机制（如MySQL的`AUTO_INCREMENT`），插入数据时无需手动设置`id`，数据库会自动生成唯一递增的值 |
| `@Column(name = "xxx")` | 普通字段（`username`等） | 定义Java字段与数据库列的映射关系 | `name`属性指定数据库列名（如`User_Name`对应表中的`User_Name`列）；若不指定，默认列名与Java字段名一致（如`username`对应`username`列） |


##### 2. 数据校验注解（ Jakarta Validation）
这些注解用于在接收前端请求数据时，自动校验字段的合法性，避免无效数据进入系统。

| 注解 | 位置 | 作用 | 细节 |
|------|------|------|------|
| `@NotBlank(message = "xxx")` | `username`、`password`、`email`字段 | 校验字符串字段**非空且非空白** | 规则：字段不能为`null`，且去除首尾空格后长度>0（比`@NotEmpty`严格，`@NotEmpty`允许纯空格）；`message`为校验失败时的提示信息 |


#### 二、字段设计与映射关系
`Users`类的每个字段都对应数据库表`table_user`中的一列，具体映射关系如下：

| Java字段名 | 类型 | 对应数据库列名 | 作用 | 约束 |
|------------|------|----------------|------|------|
| `id` | `Integer` | `User_Id` | 主键，唯一标识一条用户记录 | 自增（数据库自动生成），非空 |
| `username` | `String` | `User_Name` | 用户名（登录/显示用） | 非空（`@NotBlank`），对应数据库列非空 |
| `password` | `String` | `User_Password` | 用户密码 | 非空（`@NotBlank`），对应数据库列非空 |
| `email` | `String` | `User_Email` | 用户邮箱（用于找回密码等） | 非空（`@NotBlank`），对应数据库列非空 |

> 注：数据库表`table_user`的结构会根据该类自动生成（若开启JPA的自动建表配置），列的类型由Java字段类型推导（如`String`对应数据库的`VARCHAR`）。


#### 三、核心功能与作用
1. **ORM映射：连接Java与数据库**  
   通过JPA注解，框架（如Spring Data JPA）会自动完成：  
   - 当保存`Users`对象时，自动转换为`INSERT`语句，将数据存入`table_user`表；  
   - 当查询用户时，自动将`SELECT`语句的结果转换为`Users`对象。  
   开发者无需手动编写SQL，直接操作Java对象即可完成数据库交互。

2. **数据封装：在各层之间传递数据**  
   作为POJO，它是数据的“载体”：  
   - 前端发送的JSON数据会被解析为`Users`对象（通过`@RequestBody`）；  
   - 服务层（Service）处理后，将`Users`对象传递给数据访问层（Repository）进行持久化；  
   - 查询结果以`Users`对象形式返回给前端。

3. **参数校验：拦截无效请求**  
   配合控制器中的`@Valid`注解（如`add(@Valid @RequestBody Users user)`），在请求到达服务层前自动校验：  
   - 若前端未传`username`（或传空字符串），校验失败，直接返回400错误，提示“用户名不能为空”；  
   - 避免无效数据进入业务逻辑，减少后续处理的异常。





### 定义controller
#### 这是一个名为 `user_controller.java` 的 Spring Boot 控制器类文件，主要用于处理与用户相关的 HTTP 请求，具体信息如下：

1. **包与依赖导入**
   - 位于包 `com.chen.spring_boot_mysql.controller` 下
   - 导入了用户实体类 `Users`、用户服务接口 `IUserservice` 及实现类 `Userservice` 等相关依赖，还包含 Spring 框架及参数校验相关的类

2. **控制器配置**
   - 使用 `@RestController` 注解标识为 REST 风格控制器，表明该类中的方法会返回 JSON 等响应数据
   - 通过 `@RequestMapping("/user")` 指定基础请求路径，所有该控制器中的接口都以 `/user` 为前缀

3. **依赖注入**
   - 使用 `@Autowired` 注解注入了 `IUserservice` 类型的服务实例 `Userservice`，用于调用服务层的方法处理业务逻辑

4. **接口定义**
   - `@PostMapping("/test")`：处理 POST 请求，路径为 `/user/test`，返回字符串 "POST Test Success!"，用于测试 POST 请求是否正常
   - `@PostMapping("/add")`：处理 POST 请求，路径为 `/user/add`，用于添加用户。方法参数使用 `@Valid` 进行参数校验，`@RequestBody` 接收请求体中的 JSON 数据并映射为 `Users` 对象，调用 `Userservice` 的 `addUser` 方法完成添加操作后，返回 "add success!"

5. **待实现功能**
   - 代码中预留了查询、删除用户的注释，表明这些功能尚未实现
#### hello_controller
只需输入localhost:8080/hello使用get方法即可显示hello
```java
package com.chen.spring_boot_mysql.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/hello")
public class hello_controller {
    @GetMapping("")
    public String hello_controller() {
        return "hello";
    }
}
```

### 11月3日更新 已经完成add操作
<img width="1069" height="587" alt="image" src="https://github.com/user-attachments/assets/ade6113e-379a-44bf-94cf-32792ad9184f" />
<img width="663" height="412" alt="image" src="https://github.com/user-attachments/assets/dc70cccb-1a37-43a1-b663-787701a97e21" />

