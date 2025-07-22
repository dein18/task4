-- auto-generated definition, ознакомления ради)
create table users
(
    id   bigint generated always as identity
        constraint "User_pkey"
            primary key,
    name varchar
);

alter table users
    owner to postgres;

create unique index un_name
    on users (name);