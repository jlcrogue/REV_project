create schema bank;
create table bank.login(
	id SERIAL primary key,
	username VARCHAR (50),
	password VARCHAR (50)
);
create table bank.users(
	id SERIAL primary key,
	fname VARCHAR (100),
	mname VARCHAR (100),
	lname VARCHAR (100),
	ssn int,
	dob DATE,
	login_id int,
	constraint user_id_fk foreign key (login_id) references bank.login(id)
);
create table bank.accounts(
	num SERIAL primary key,
	type VARCHAR (20),
	funds DECIMAL,
	login_id int,
	constraint user_id_fk foreign key (login_id) references bank.login(id)
);

select * from bank.accounts;
select * from bank.login;
select * from bank.users;

create user customers with password 'password';
grant select, update, insert, delete on bank.login to customers;
grant select, update, insert, delete on bank.users to customers;
grant select, update, insert, delete on bank.accounts to customers;
grant all privileges on all tables in schema bank to customers;
grant all privileges on schema bank to customers;

insert into bank.login values(default, 'unibomber', 'kaboom!');
insert into bank.login values(default, 'HisExcellency', 'HelicopterRides1974');
select * from bank.login;

insert into bank.users values(default, 'Theodore', 'John', 'Kaczynski', 123456789, '5/22/1942', 1);
insert into bank.users values(default, 'Augusto', 'Jose Ramon', 'Pinochet', 987654321, '11/25/1915', 2);
select * from bank.users;

insert into bank.accounts values(default, 'checking', '127.54', 1);
insert into bank.accounts values(default, 'savings', '5.01', 1);
insert into bank.accounts values(default, 'checking', '4253761.29', 2);
insert into bank.accounts values(default, 'savings', '12788321.48', 2);
select * from bank.accounts;

alter table bank.login add column type VARCHAR (50);
update bank.login set type='customer';
select * from bank.login;

alter table bank.accounts add column status VARCHAR (50);
update bank.accounts set status='active';
select * from bank.accounts;

insert into bank.login values(6, 'banker01', 'password', 'employee');
insert into bank.login values(7, 'banker02', 'password', 'employee');
select * from bank.login;

/*drop table bank.accounts cascade;*/