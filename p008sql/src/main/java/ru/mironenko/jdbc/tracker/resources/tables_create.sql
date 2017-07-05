create table items(
id serial primary key,
name varchar(200),
description varchar(500),
create_time timestamp
);

create table comments(
id serial primary key,
item_id integer references items(id),
text varchar(2000)
);