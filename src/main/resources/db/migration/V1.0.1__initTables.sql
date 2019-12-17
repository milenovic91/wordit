insert into users (username, password, email) values ('miki', '123', 'miki@test.com');

insert into articles(slug, user_id, title, description, body, created_at) values ('hello-world', 1, 'Hello world', '', 'My first article', current_timestamp);
insert into articles(slug, user_id, title, description, body, created_at) values ('second-article', 1, 'Second Article', '', 'My second article', current_timestamp);
