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