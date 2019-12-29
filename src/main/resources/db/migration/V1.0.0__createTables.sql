create table users (
  id varchar(255) auto_increment not null primary key,
  username varchar(255) unique,
  password varchar(255),
  email varchar(255) unique,
  bio text,
  image varchar(511)
);

create table follows (
  id varchar(255) not null primary key auto_increment,
  follower_id varchar(255) not null,
  followee_id varchar(255) not null,
  created_at timestamp not null default current_timestamp,
  constraint `follower`
    foreign key (follower_id)
    references users (id)
    on delete cascade
    on update cascade,
  constraint `followee`
    foreign key (followee_id)
    references users (id)
    on delete cascade
    on update cascade
);

create table articles (
  id varchar(255) auto_increment not null primary key,
  slug varchar(255) unique,
  user_id varchar(255),
  title varchar(255),
  description varchar(255),
  body text,
  created_at timestamp not null default current_timestamp,
  updated_at timestamp not null default current_timestamp on update current_timestamp
);

create table article_favorites (
  article_id int(11) not null,
  user_id int(11) not null,
  primary key (article_id, user_id),
  constraint `article_id_fk`
    foreign key (article_id) references articles(id)
    on delete cascade
    on update cascade,
  constraint `user_id_fk`
    foreign key (user_id) references users(id)
	on delete cascade
    on update cascade
);