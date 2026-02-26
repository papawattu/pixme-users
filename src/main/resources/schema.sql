CREATE TABLE IF NOT EXISTS users (
  id varchar(36) not null,
  name varchar(100) not null,
  email varchar(100) not null,
  PRIMARY KEY ( id )
);