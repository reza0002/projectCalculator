INSERT INTO user (name, email, password)
VALUES ('Anders Jensen', 'anders@mail.dk', '1234'),
       ('Maria Nielsen', 'maria@mail.dk', '1234'),
       ('Sofie Hansen', 'sofie@mail.dk', '1234'),
       ('Mikkel Larsen', 'mikkel@mail.dk', '1234'),
       ('Emma Pedersen', 'emma@mail.dk', '1234'),
       ('Lucas Christensen', 'lucas@mail.dk', '1234'),
       ('Freja Madsen', 'freja@mail.dk', '1234'),
       ('Oliver Thomsen', 'oliver@mail.dk', '1234'),
       ('Clara Poulsen', 'clara@mail.dk', '1234'),
       ('William Mortensen', 'william@mail.dk', '1234');

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
VALUES ('Frontend Development', 'Udvikling af frontend', 120, 700, 1, 0),
       ('Backend API', 'REST API udvikling', 180, 850, 1, 0),

       ('iOS App', 'Udvikling til iOS', 160, 800, 2, 0),
       ('Android App', 'Udvikling til Android', 170, 800, 2, 1),

       ('Employee Management', 'Håndtering af medarbejdere', 140, 750, 3, 1),
       ('Vacation Module', 'Ferie og fravær', 90, 650, 1, 1),

       ('Payment System', 'Betalingsgateway integration', 130, 900, 4, 0),
       ('Product Catalog', 'Produkt administration', 110, 750, 4, 0),

       ('Statistics Engine', 'Databehandling', 200, 950, 5, 0),
       ('Graph Components', 'Grafer og visualisering', 95, 850, 5, 0);

INSERT INTO task
    (name, hours, price_per_hour, sub_project_id, is_done)
VALUES ('Landing Page', 20, 700, 1, 1),
       ('Navigation Menu', 15, 700, 1, 0),
       ('User Authentication', 30, 850, 2, 1),
       ('Database Setup', 25, 850, 2, 0),

       ('Login Screen', 18, 800, 3, 1),
       ('Push Notifications', 22, 800, 3, 0),
       ('Android UI', 20, 800, 4, 1),
       ('Play Store Deployment', 16, 800, 4, 1),

       ('Employee CRUD', 35, 750, 5, 1),
       ('Role Permissions', 28, 750, 5, 1),
       ('Vacation Requests', 15, 650, 6, 1),

       ('Stripe Integration', 40, 900, 7, 0),
       ('Checkout Flow', 32, 900, 7, 0),
       ('Product Search', 24, 750, 8, 1),

       ('Generate Reports', 50, 950, 9, 0),
       ('Export Data', 18, 950, 9, 0),
       ('Bar Charts', 20, 850, 10, 1),
       ('Responsive Dashboard', 26, 850, 10, 0);