-- Only Postgress table-droping
DROP TABLE IF EXISTS phone_note;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS token;
DROP TABLE IF EXISTS persistent_logins;
DROP TABLE IF EXISTS ph_user;



CREATE TABLE ph_user
(
    id           INTEGER PRIMARY KEY,
    firstName    VARCHAR(255),
    email        VARCHAR(255),
    hashPassword VARCHAR(255),
    authority    VARCHAR(255) NOT NULL
);

CREATE TABLE phone_note
(
    id           INTEGER PRIMARY KEY,
    phone_number VARCHAR(255),
    text         VARCHAR(1020),
    state        VARCHAR(255)            NOT NULL,
    createdTime  TIMESTAMP DEFAULT now() NOT NULL,
    user_id      INTEGER                 NOT NULL,

    FOREIGN KEY (user_id) REFERENCES ph_user (id)
);
