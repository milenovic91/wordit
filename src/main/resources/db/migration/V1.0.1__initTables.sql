insert into users (username, password, email) values ('milos', 'password', 'milos@test.com');
insert into users (username, password, email) values ('marko', 'password', 'marko@test.com');
insert into users (username, password, email) values ('ana', 'password', 'ana@test.com');

insert into follows(follower_id, followee_id) values (1, 2); -- milos follows marko
insert into follows(follower_id, followee_id) values (1, 3); -- milos follows ana
insert into follows(follower_id, followee_id) values (3, 2); -- ana follows marko

insert into articles(slug, user_id, title, description, body) values
  ('hello-world', 1, 'Hello World', 'Hello World', 'This is hello world example.'),
  ('how-to-get-started-with-docker', 1, 'How to get started with Docker', 'Get started with docker', 'Docker is a set of platform as a service products that use OS-level virtualization to deliver software in packages called containers.'),
  ('hyperx-cloud-II', 2, 'HyperX cloud II', 'Great headsets for afordable price', 'HYPERX Cloud II Headset Pro Gaming Headset (Red) - KHX-HSCP-RD Virtual Surround 7.1, 53mm, Neodimijum, 15Hz - 25kHz');

insert into article_favorites values
  (1, 3), -- ana likes hello world
  (2, 3); -- ana likes docker
