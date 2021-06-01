create schema alphagr5;

use alphagr5;

create table users
(
    useremail    varchar(50),
    userpassword varchar(20),
    primary key (useremail)
);

create table roles
(
    roleID   integer not null,
    rolename varchar(50),
    primary key (roleID)
);

create table user_roles
(
    useremail varchar(50),
    roleID    integer not null,
    foreign key (useremail) references users (useremail) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (roleID) references roles (roleID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key (useremail, roleID)
);
create table tasks
(
    taskID        integer not null auto_increment,
    title         varchar(20),
    descriptions  varchar(1000),
    startdate     date,
    deadline      date,
    estimatedtime int     not null,
    primary key (taskID)
);

create table user_tasks
(
    useremail varchar(50),
    taskID    integer not null,
    foreign key (useremail) references users (useremail) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (taskID) references tasks (taskID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key (useremail, taskID)
);

create table projects
(
    useremail    varchar(50),
    projectID    integer not null auto_increment,
    title        varchar(20),
    descriptions varchar(1000),
    startdate    date,
    deadline     date,
    primary key (projectID),
    foreign key (useremail) references users (useremail)
);

create table project_tasks
(
    projectID integer not null,
    taskID    integer not null,
    foreign key (projectID) references projects (projectID) ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (taskID) references tasks (taskID) ON DELETE CASCADE ON UPDATE CASCADE,
    primary key (projectID, taskID)
);
