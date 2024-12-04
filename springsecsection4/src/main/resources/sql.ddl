create database eazyBank;
use  eazyBank;
create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert into users values('user', '{noop}EazyBytes@12345', '1');
insert into authorities values('user', 'read');

insert into users values('admin', '{bcrypt}$2a$12$RPQZsLtvJ1ihS3cGvm70AOP84XRvzcQEParA8iW/UwZ8dkB/.VJcC', '1');
insert into authorities values('admin', 'admin');


create table customer
( id int not null auto_increment,
  email varchar(45) not null,
  pwd varchar(200) not null,
  user_role varchar(45) not null,
  primary key(id)
 );


 insert into customer(email,pwd,user_role) values('jhon@example.com', '{noop}EazyBytes@12345', 'read');

 // pwd- admin@12345
 insert into customer(email,pwd,user_role) values('admin@example.com', '{bcrypt}$2a$12$.ArjkUzFqr0XtVfV0AlQKeUUKk0ClMmOeiFCOA9TUA4NV6xZ9wymO', 'admin');
 insert into customer(email,pwd,user_role) values('jhon@example.com', '{noop}EazyBytes@12345', 'read');
