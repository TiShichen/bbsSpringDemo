## Demo BBS based on Spring Boot

## 目标效果网站  
https://elasticsearch.cn/

## 采用资源  
[Bootstrap](https://getbootstrap.com/)  
[FontAwesome](https://fontawesome.com/)  
[jQuery](https://jquery.com/)
[Lombok](https://www.projectlombok.org/)  
[Thymeleaf Tutorial - Setting attribute values](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
[SpringBoot Devtools](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#using-boot-devtools)

## Tips  
When database does not work when arribute has camel case, try configuring myBatis in application.properties:
```
mybatis.configuration.map-underscore-to-camel-case=true
```
[Time Format (Same usage as SQL time format)](https://www.w3school.com.cn/sql/func_date_format.asp)

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