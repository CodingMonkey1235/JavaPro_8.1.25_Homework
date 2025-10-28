alter table service_users
add column if not exists group_id bigserial references service_users_groups (id)
