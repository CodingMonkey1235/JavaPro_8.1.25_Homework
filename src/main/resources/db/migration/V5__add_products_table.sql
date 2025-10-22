DROP TABLE IF EXISTS products;

create table products
(
    id bigserial primary key,
    account_number varchar(255),
    balance decimal,
    product_type varchar(255),
    user_id bigserial references service_users (id)
);

insert into products (account_number, balance, product_type, user_id)
values ('account_number_1', 5, 'ACCOUNT', 4),
    ('account_number_2', 10, 'ACCOUNT', 5),
    ('account_number_3', 15, 'CARD', 6);
