insert into users (username, password, email) values ('milos', 'password', 'milos@test.com');
insert into users (username, password, email) values ('marko', 'password', 'marko@test.com');
insert into users (username, password, email) values ('ana', 'password', 'ana@test.com');

insert into follows(follower_id, followee_id) values (1, 2);
insert into follows(follower_id, followee_id) values (1, 3);
insert into follows(follower_id, followee_id) values (3, 2);

insert into articles(slug, user_id, title, description, body, created_at) values ('hello-world', 1, 'Hello world', '', 'My first article', current_timestamp);
insert into articles(slug, user_id, title, description, body, created_at) values ('second-article', 1, 'Second Article', '', 'My second article', current_timestamp);
insert into articles(slug, user_id, title, description, body, created_at) values ('netflix-aws', 2, 'Netflix aws', '', 'about it', current_timestamp);
