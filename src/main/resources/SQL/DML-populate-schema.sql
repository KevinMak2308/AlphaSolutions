use alphagr5;

INSERT INTO users (useremail, userpassword)
VALUES ('admin@gmail.com', '246');

INSERT INTO roles (roleID, rolename)
VALUES (2, 'Manager');
INSERT INTO roles (roleID, rolename)
VALUES (1, 'User');
INSERT INTO roles (roleID, rolename)
VALUES (3, 'Admin');

INSERT INTO user_roles (useremail, roleID)
VALUES ('admin@gmail.com', 3);