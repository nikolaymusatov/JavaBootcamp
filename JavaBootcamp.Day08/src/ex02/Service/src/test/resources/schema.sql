CREATE TABLE IF NOT EXISTS users_with_passwords(
  id BIGINT PRIMARY KEY,
  email VARCHAR(30),
  password VARCHAR(30)
);