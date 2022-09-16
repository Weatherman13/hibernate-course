CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY ,
    firstname VARCHAR(128) ,
    lastname VARCHAR(128),
    birth_date DATE,
    username VARCHAR(128) unique ,
    role VARCHAR(32),
    company_id INT REFERENCES company (id)
);
create table profile (  id bigserial primary key ,
                         user_id BIGINT not null unique references users(id),
                         street varchar(128),
                         language CHAR(2)
)

create table company (
                         id SERIAL PRIMARY KEY ,
                         name VARCHAR(64) not null unique
)

drop table company
drop table users
drop table profile