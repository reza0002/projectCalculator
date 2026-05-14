INSERT INTO user (name, email, password)
VALUES ('Anders Jensen', 'anders@mail.dk', 'password123'),
       ('Sofie Nielsen', 'sofie@mail.dk', 'password123'),
       ('Mikkel Hansen', 'mikkel@mail.dk', 'password123'),
       ('Laura Pedersen', 'laura@mail.dk', 'password123'),
       ('Christian Møller', 'christian@mail.dk', 'password123'),
       ('Emma Christensen', 'emma@mail.dk', 'password123'),
       ('Jonas Larsen', 'jonas@mail.dk', 'password123'),
       ('Maria Andersen', 'maria@mail.dk', 'password123'),
       ('Thomas Madsen', 'thomas@mail.dk', 'password123'),
       ('Julie Olsen', 'julie@mail.dk', 'password123');

INSERT INTO project (name, project_leader)
VALUES ('Hjemmeside redesign', 1),
       ('App udvikling', 2),
       ('Database migration', 3);

INSERT INTO user_project (user_id, project_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 2),
       (5, 2),
       (6, 2),
       (7, 3),
       (8, 3),
       (9, 3),
       (10, 3);

INSERT INTO sub_project (description, hours, project_id)
VALUES ('Design mockups', 10, 1),
       ('Frontend udvikling', 20, 1),
       ('Backend API', 30, 2),
       ('Database setup', 15, 2),
       ('Data migration script', 25, 3);

INSERT INTO task (name, hours, price_per_hour, sub_project_id)
VALUES ('Lav wireframes', 3, 500, 1),
       ('Farvepalette', 2, 500, 1),
       ('Lav login side', 5, 600, 2),
       ('Lav dashboard', 8, 600, 2),
       ('Lav REST endpoints', 10, 700, 3),
       ('Autentificering', 8, 700, 3),
       ('Opret tabeller', 4, 550, 4),
       ('Migrer gamle data', 12, 600, 5);