CREATE TABLE users
(
    id         BIGSERIAL PRIMARY KEY,
    firstname  VARCHAR(128),
    lastname   VARCHAR(128),
    birth_date DATE,
    username   VARCHAR(128) unique,
    role       VARCHAR(32),
    company_id INT REFERENCES company (id)
);

create table company_locale(
    company_id int not null references company(id),
     lang char(2) not null ,
     description varchar(128) not null ,
     PRIMARY KEY (company_id,lang)
);

create table profile
(
    id       bigserial primary key,
    user_id  BIGINT not null unique references users (id),
    street   varchar(128),
    language CHAR(2)
);

CREATE TABLE users_chat
(
    id         bigserial primary key,
    user_id    BIGINT REFERENCES users (id),
    chat_id    BIGINT REFERENCES chat (id),
    created_at timestamp    not null,
    created_by varchar(128) not null
);

drop table users_chat;

create table chat
(
    id   bigserial primary key,
    name varchar(64) not null unique
);

create table company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) not null unique
);

drop table company;
drop table users;
drop table profile