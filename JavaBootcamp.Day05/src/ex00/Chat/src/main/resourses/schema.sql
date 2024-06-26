CREATE TABLE IF NOT EXISTS users (
  id serial NOT NULL,
  login VARCHAR NOT NULL UNIQUE,
  password VARCHAR NOT NULL,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS chatrooms (
  id serial NOT NULL,
  name VARCHAR NOT NULL UNIQUE,
  owner int REFERENCES users (id) ON DELETE CASCADE,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS messages (
  id serial NOT NULL,
  author int REFERENCES users (id) ON DELETE CASCADE,
  room int REFERENCES chatrooms (id) ON DELETE CASCADE,
  text text,
  date TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS chatroom_users (
  chatroom_id int REFERENCES chatrooms (id) ON DELETE CASCADE,
  user_id int REFERENCES users (id) ON DELETE CASCADE
);
/*
 DROP TABLE IF EXISTS users CASCADE;
 DROP TABLE IF EXISTS chatrooms CASCADE;
 DROP TABLE IF EXISTS messages CASCADE;
 DROP TABLE IF EXISTS chatroom_users CASCADE;
 */