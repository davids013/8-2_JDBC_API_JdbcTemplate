create schema myschema;

create table CUSTOMERS
(
    id           int primary key auto_increment,
    name         varchar(32) not null,
    surname      varchar(32),
    age          int         not null check ( age > 0 and age < 110 ),
    phone_number varchar(32) unique
);

create table ORDERS
(
    id           int primary key auto_increment,
    date         timestamp   not null default now(),
    customer_id  int,
    product_name varchar(64) not null,
    amount       int         not null,
    foreign key (customer_id) references CUSTOMERS (id)
);