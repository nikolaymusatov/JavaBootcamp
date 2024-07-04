CREATE TABLE day09ex01_users(
  id SERIAL PRIMARY KEY,
  login VARCHAR,
  password VARCHAR,
  authorized boolean
);
CREATE TABLE day09ex01_messages (
  id SERIAL PRIMARY KEY,
  author BIGINT REFERENCES day09ex01_users (id) ON DELETE CASCADE,
  text text,
  date TIMESTAMP
);