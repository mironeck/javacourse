create table users (
	id serial primary key,
	name character varying (50)
);

create table roles (
	id serial primary key,
	user_id integer references users(id),
	role character varying (50)
);

create table rights_of_roles (
	id serial primary key,
	role character varying (50),
	rights_of_role character varying (50)

);

create table orders (
	id serial primary key,
	user_id integer references users(id),
	order_of_user character varying (200)
);

create table comments (
	id serial primary key,
	order_id integer references orders(id),
	comment text
);

create table files (
	id serial primary key,
	order_id integer references orders(id),
	file_name character varying(50),
	file_data bytea
);

create table condition (
	id serial primary key,
	order_id integer references orders(id),
	conditon character varying(100)
);

create table category (
	id serial primary key,
	order_id integer references orders(id),
	category character varying(100)
);


insert into users(name) values ('Jack');
insert into users(name) values ('Miroslav');
insert into users(name) values ('Iosif');

insert into roles(user_id, role) values ('1', 'Admin');
insert into roles(user_id, role) values ('2', 'User');
insert into roles(user_id, role) values ('3', 'Programmer');

insert into rights_of_roles(role, rights_of_role) values ('Admin', 'Get rights, get access, change relationship');
insert into rights_of_roles(role, rights_of_role) values ('User', 'Create orders');
insert into rights_of_roles(role, rights_of_role) values ('Programmer', 'Create orders, add changes');

insert into orders(user_id, order_of_user) values ('2', 'Do something one');
insert into orders(user_id, order_of_user) values ('2', 'Do something two');
insert into orders(user_id, order_of_user) values ('3', 'Do something strange');

insert into comments(order_id, comment) values ('1', 'comment one to do smth one');
insert into comments(order_id, comment) values ('1', 'comment two to do smth one');
insert into comments(order_id, comment) values ('2', 'comment one to do smth two');

insert into condition(order_id, conditon) values ('1', 'in process', '1');
insert into condition(order_id, conditon) values ('2', 'in process', '1');
insert into condition(order_id, conditon) values ('3', 'complete', '2');

insert into category(order_id, category) values ('1', '1');
insert into category(order_id, category) values ('2', '1');
insert into category(order_id, category) values ('3', '2');