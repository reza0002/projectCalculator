INSERT INTO user (name, email)
VALUES ('Anders Jensen', 'anders@mail.dk'),
       ('Maria Nielsen', 'maria@mail.dk');

INSERT INTO project (name, project_leader, description, is_done)
VALUES ('Test Project', 1, 'Test Description', 0);

INSERT INTO user_project (user_id, project_id)
VALUES (1, 1),
       (2, 1);

INSERT INTO sub_project (name, description, hours, price_per_hour, project_id, is_done)
VALUES ('Backend API', 'REST API udvikling', 0, 1200, 1, 0);

INSERT INTO task (name, hours, price_per_hour, sub_project_id, is_done, user_id)
VALUES ('Database Setup', 3, 1200, 1, 0, 1);