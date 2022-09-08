CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY ,
    firstname VARCHAR(128) ,
    lastname VARCHAR(128),
    birth_date DATE,
    username VARCHAR(128) unique ,
    role VARCHAR(32),
    company_id INT REFERENCES company (id) ON DELETE CASCADE
);

create table company (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(64) not null unique
)

drop table company
drop table users