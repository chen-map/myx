# HUST-Spring_Boot入门计划
***这是一个从零开始学习spring_boot 的文档，现在开始还不晚哦*** 


## 1.spring_boot_hello
最开始的地方：如何使用spring_Boot完成web的访问？


## 2.spring_boot_mysql
我的人生中第一个项目，如何使用JPA映射到mysql上?
#### 11月3日 update
完成了add的调用，输入localhost:8080/user/add 使用POST方法传参json文件
##### 请求
```html
POST /user/add HTTP/1.1
Host: 10.22.61.242:8080
Accept-Language: zh-CN,zh;q=0.9
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.6778.140 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7
Accept-Encoding: gzip, deflate, br
Connection: keep-alive
Content-Type: application/json 
Content-Length: 93

{
    "username": "testuser",
    "password": "123456",
    "email": "test@example.com"
}
```
##### 响应
```html
HTTP/1.1 200 
Content-Type: text/html;charset=UTF-8
Content-Length: 12
Date: Mon, 03 Nov 2025 11:51:15 GMT
Keep-Alive: timeout=60
Connection: keep-alive

add success!
```
### 11.6更新查找和删除操作
加上了ai帮忙编写的前端html，可以简易的增加用户，查询，删除
##### 增加
<img width="1919" height="973" alt="image" src="https://github.com/user-attachments/assets/359e85bd-a588-4041-a91b-ac720ac10202" />
 查询
<img width="808" height="495" alt="image" src="https://github.com/user-attachments/assets/c87fbee5-6286-4655-ad0e-7e01e8b6634c" />
 删除
<img width="817" height="286" alt="image" src="https://github.com/user-attachments/assets/6d9e5e7d-842e-488e-ba52-a9933e5081e5" />



