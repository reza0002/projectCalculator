DROP SCHEMA IF EXISTS calculator;
CREATE SCHEMA calculator;
USE
calculator;

CREATE TABLE user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(250) NOT NULL,
    email    VARCHAR(250) NOT NULL,
);

CREATE TABLE project
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(250) NOT NULL,
    project_leader INT          NOT NULL,
    description    TEXT,
    is_done        TINYINT(1) NOT NULL,
    FOREIGN KEY (project_leader) REFERENCES user (id)
);

CREATE TABLE user_project
(
    user_id    INT NOT NULL,
    project_id INT NOT NULL,
    PRIMARY KEY (user_id, project_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE sub_project
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(250) NOT NULL,
    description    TEXT         NOT NULL,
    hours          INT          NOT NULL,
    price_per_hour INT          NOT NULL,
    project_id     INT          NOT NULL,
    is_done        TINYINT(1) NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE task
(
    id             INT AUTO_INCREMENT PRIMARY KEY,
    name           VARCHAR(250) NOT NULL,
    hours          INT          NOT NULL,
    price_per_hour INT          NOT NULL,
    sub_project_id INT          NOT NULL,
    is_done        TINYINT(1) NOT NULL,
    FOREIGN KEY (sub_project_id) REFERENCES sub_project (id)
);