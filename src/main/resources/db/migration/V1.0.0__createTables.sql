create table users (
  id varchar(255) auto_increment not null primary key,
  username varchar(255) unique,
  password varchar(255),
  email varchar(255) unique,
  bio text,
  image varchar(511)
);

create table articles (
  id varchar(255) auto_increment not null primary key,
  slug varchar(255) unique,
  user_id varchar(255),
  title varchar(255),
  description varchar(255),
  body text,
  created_at timestamp not null,
  updated_at timestamp not null default current_timestamp
);