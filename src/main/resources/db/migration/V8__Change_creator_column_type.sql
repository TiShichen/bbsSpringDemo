alter table comment modify commentator bigint not null comment 'ID of the user who created this comment';
alter table question modify creator bigint not null;
