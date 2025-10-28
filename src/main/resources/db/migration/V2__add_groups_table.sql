DROP TABLE IF EXISTS service_users_groups;

create table service_users_groups
(
    id bigserial primary key,
    name varchar(255)
);

insert into service_users_groups (name)
values ('administrators'), ('chat_moderators'), ('premium_users'), ('standard_users');