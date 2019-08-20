## Demo BBS based on Spring Boot

## 目标效果网站  
https://elasticsearch.cn/

## Resources  
[Bootstrap](https://getbootstrap.com/)  
[FontAwesome](https://fontawesome.com/)  
[jQuery](https://jquery.com/)  
[Lombok](https://www.projectlombok.org/)  
[Thymeleaf Tutorial - Setting attribute values](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)  
[SpringBoot Devtools](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)   
[Spring MVC Book](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc)  

## Tips  
When database does not work when arribute has camel case, try configuring myBatis in application.properties:
```
mybatis.configuration.map-underscore-to-camel-case=true
```

[Time Format (Same usage as SQL time format)](https://www.w3school.com.cn/sql/func_date_format.asp)  

| 格式 | 描述                              |
|:----:|:---------------------------------:|
| %a | 缩写星期名                           |
| %b | 缩写月名                            |
| %c | 月，数值                            |
| %D | 带有英文前缀的月中的天                     |
| %d | 月的天，数值\(00\-31\)                |
| %e | 月的天，数值\(0\-31\)                 |
| %f | 微秒                              |
| %H | 小时 \(00\-23\)                   |
| %h | 小时 \(01\-12\)                   |
| %I | 小时 \(01\-12\)                   |
| %i | 分钟，数值\(00\-59\)                 |
| %j | 年的天 \(001\-366\)                |
| %k | 小时 \(0\-23\)                    |
| %l | 小时 \(1\-12\)                    |
| %M | 月名                              |
| %m | 月，数值\(00\-12\)                  |
| %p | AM 或 PM                         |
| %r | 时间，12\-小时（hh:mm:ss AM 或 PM）     |
| %S | 秒\(00\-59\)                     |
| %s | 秒\(00\-59\)                     |
| %T | 时间, 24\-小时 \(hh:mm:ss\)         |
| %U | 周 \(00\-53\) 星期日是一周的第一天         |
| %u | 周 \(00\-53\) 星期一是一周的第一天         |
| %V | 周 \(01\-53\) 星期日是一周的第一天，与 %X 使用 |
| %v | 周 \(01\-53\) 星期一是一周的第一天，与 %x 使用 |
| %W | 星期名                             |
| %w | 周的天 （0=星期日, 6=星期六）              |
| %X | 年，其中的星期日是周的第一天，4 位，与 %V 使用      |
| %x | 年，其中的星期一是周的第一天，4 位，与 %v 使用      |
| %Y | 年，4 位                           |
| %y | 年，2 位                           |
转自W3School <sup>©</sup>

Get current full URL or current URI(or use .getRequestURI)
```html
<div th:text="${#httpServletRequest.requestURL +'?'+ #httpServletRequest.queryString }"></div>
<div th:text="${#httpServletRequest.requestURI}"></div>
```


# Thymeleaf
Converting system mills time to date
```html
th:text="${#dates.format(question.gmtCreate,'dd MMMM yyyy')}"
th:text="${#dates.format(question.gmtCreate,'dd MMMM yyyy HH:mm')}"
```

## SQL  
Create MySql table
```sql
create table user
(
	id int auto_increment
		primary key,
	account varchar(100) null,
	name varchar(50) null,
	token char(36) null,
	gmt_create bigint(255) null,
	gmt_modified bigint(255) null
);
```

## Flyway
flyway db.migration file naming scheme(Double under scroll after V1.2):
```
V1.2__Description.sql
``` 

flyway migrate script
```bash
mvn flyway:migrate
```