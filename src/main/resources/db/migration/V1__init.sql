DROP TABLE IF EXISTS service_users;

create table service_users
(
    id bigserial primary key,
    username varchar(50),
    email varchar(50)
);

insert into service_users (username, email)
values ('zero_user', 'admin@mail.ru'), ('first_user', 'first_user@mail.ru'), ('second_user', 'second_user@mail.ru');
