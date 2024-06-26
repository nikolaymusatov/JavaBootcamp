INSERT INTO users
VALUES (DEFAULT, 'carisafi', 'qwerty123'),
  (DEFAULT, 'grapefru', 'school21'),
  (DEFAULT, 'torncorn', 'diamonds2020'),
  (DEFAULT, 'leilanil', 'bluenissan228'),
  (DEFAULT, 'chiquita', 'friedchicken22');
INSERT INTO chatrooms
VALUES (DEFAULT, 'diamonds_tribe', 1),
  (DEFAULT, 'java_projects', 1),
  (DEFAULT, 'cpp_projects', 5),
  (DEFAULT, 'sql_projects', 5),
  (DEFAULT, 'free_chat', 3);
INSERT INTO messages
VALUES (
    DEFAULT,
    1,
    1,
    'Hello, everybody! We are all diamonds here!',
    current_timestamp
  ),
  (
    DEFAULT,
    2,
    3,
    'Hey! Did someone do SmartCalc already?',
    current_timestamp
  ),
  (
    DEFAULT,
    3,
    1,
    'Hey, carisafi, what are we doing today?',
    current_timestamp
  ),
  (
    DEFAULT,
    4,
    3,
    'I cant figure out how we should process an expression line!',
    current_timestamp
  ),
  (
    DEFAULT,
    5,
    4,
    'Guys, im stuck with s21_info here, can anybody help?',
    current_timestamp
  );
INSERT INTO chatroom_users
VALUES (1, 1),
  (1, 3),
  (1, 5),
  (2, 1),
  (3, 1),
  (3, 2),
  (3, 3),
  (3, 4),
  (3, 5),
  (4, 1),
  (4, 2),
  (4, 5),
  (5, 1),
  (5, 2),
  (5, 3),
  (5, 4),
  (5, 5);