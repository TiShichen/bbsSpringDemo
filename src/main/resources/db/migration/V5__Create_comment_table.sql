create table comment
(
	id bigint auto_increment,
	parent_id bigint not null comment 'Parent ID',
	type int null comment 'Parent type',
	commentator int not null comment 'ID of the user who created this comment',
	gmt_create bigint not null comment 'Create time',
	gmt_modified bigint not null comment 'Modify time',
	like_count bigint default 0 null,
	constraint comment_pk
		primary key (id)
);