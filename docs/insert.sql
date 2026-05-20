INSERT INTO user (name, email)
VALUES ('Anders Jensen', 'anders@mail.dk'),
       ('Maria Nielsen', 'maria@mail.dk'),
       ('Sofie Hansen', 'sofie@mail.dk'),
       ('Mikkel Larsen', 'mikkel@mail.dk'),
       ('Emma Pedersen', 'emma@mail.dk'),
       ('Lucas Christensen', 'lucas@mail.dk'),
       ('Freja Madsen', 'freja@mail.dk'),
       ('Oliver Thomsen', 'oliver@mail.dk'),
       ('Clara Poulsen', 'clara@mail.dk'),
       ('William Mortensen', 'william@mail.dk');

INSERT INTO project (name, project_leader, description, is_done)
VALUES ('Website Redesign', 1, 'Redesign af virksomhedens hjemmeside', 0),
       ('Mobile App', 2, 'Udvikling af mobil applikation', 0),
       ('HR System', 3, 'Internt HR administrationssystem', 1),
       ('E-commerce Platform', 4, 'Webshop med betalingsintegration', 0),
       ('Analytics Dashboard', 5, 'Dashboard til dataanalyse', 0);

INSERT INTO user_project (user_id, project_id)
VALUES (1, 1),
       (2, 1),
       (3, 1),

       (2, 2),
       (4, 2),
       (5, 2),

       (3, 3),
       (6, 3),
       (7, 3),

       (4, 4),
       (8, 4),
       (9, 4),

       (5, 5),
       (10, 5),
       (1, 5);

INSERT INTO sub_project
    (name, description, hours, price_per_hour, project_id, is_done)
VALUES ('Frontend Development', 'Udvikling af frontend', 0, 1200, 1, 0),
       ('Backend API', 'REST API udvikling', 1, 1200, 1, 0),

       ('iOS App', 'Udvikling til iOS', 2, 1200, 2, 0),
       ('Android App', 'Udvikling til Android', 3, 1200, 2, 1),

       ('Employee Management', 'Håndtering af medarbejdere', 4, 1200, 3, 1),
       ('Vacation Module', 'Ferie og fravær', 5, 1200, 3, 1),

       ('Payment System', 'Betalingsgateway integration', 6, 1200, 4, 0),
       ('Product Catalog', 'Produkt administration', 7, 1200, 4, 0),

       ('Statistics Engine', 'Databehandling', 8, 1200, 5, 0),
       ('Graph Components', 'Grafer og visualisering', 9, 1200, 5, 0);

INSERT INTO task
    (name, hours, price_per_hour, sub_project_id, is_done)
VALUES ('Landing Page', 0, 1200, 1, 1),
       ('Navigation Menu', 1, 1200, 1, 0),
       ('User Authentication', 2, 1200, 2, 1),
       ('Database Setup', 3, 1200, 2, 0),

       ('Login Screen', 4, 1200, 3, 1),
       ('Push Notifications', 5, 1200, 3, 0),
       ('Android UI', 6, 1200, 4, 1),
       ('Play Store Deployment', 7, 1200, 4, 1),

       ('Employee CRUD', 8, 1200, 5, 1),
       ('Role Permissions', 9, 1200, 5, 1),
       ('Vacation Requests', 10, 1200, 6, 1),

       ('Stripe Integration', 0, 1200, 7, 0),
       ('Checkout Flow', 1, 1200, 7, 0),
       ('Product Search', 2, 1200, 8, 1),

       ('Generate Reports', 3, 1200, 9, 0),
       ('Export Data', 4, 1200, 9, 0),
       ('Bar Charts', 5, 1200, 10, 1),
       ('Responsive Dashboard', 6, 1200, 10, 0);