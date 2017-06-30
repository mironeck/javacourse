create table transmission (
id serial primary key,
type varchar(200)
);

insert into transmission(type) values('mechanic');
insert into transmission(type) values('robotic');
insert into transmission(type) values('automatic');

create table engine (
id serial primary key,
volume real
);

insert into engine(volume) values (1.3);
insert into engine(volume) values (1.6);
insert into engine(volume) values (1.8);
insert into engine(volume) values (2.0);
insert into engine(volume) values (2.5);

create table car_body (
id serial primary key,
type varchar(200)
);

insert into car_body(type) values ('hatchback');
insert into car_body(type) values ('minivan');
insert into car_body(type) values ('compactvan');
insert into car_body(type) values ('sedan');
insert into car_body(type) values ('universal');
insert into car_body(type) values ('liftback');
insert into car_body(type) values ('cupe');
insert into car_body(type) values ('cabriolet');
insert into car_body(type) values ('roadster');

create table car (
id serial primary key,
name varchar(200),
transmission_id int references transmission(id),
engine_id int references engine(id),
car_body_id int references car_body(id)
);


insert into car(name, transmission_id, engine_id, car_body_id) values ('Opel', 1, 2, 3);
insert into car(name, transmission_id, engine_id, car_body_id) values ('Lada', 1, 1, 4);
insert into car(name, transmission_id, engine_id, car_body_id) values ('Volvo', 3, 3, 5);

--select unused transmissiones;
select t.type from transmission as t left outer join car as c on c.transmission_id = t.id where c.id is null;
--select unused engines;
select e.volume from engine as e left outer join car as c on c.engine_id = e.id where c.id is null;
--select unused car bodies;
select b.type from car_body as b left outer join car as c on c.car_body_id = b.id where c.id is null;

--select all created cars;
select c.id, c.name, t.type, e.volume, b.type
from car c, transmission t, engine e, car_body b
where c.transmission_id = t.id 
and c.engine_id = e.id
and c.car_body_id = b.id;