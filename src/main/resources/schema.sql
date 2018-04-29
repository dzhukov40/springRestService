
DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    id integer not null,
    name varchar(255) not null,
    passport_number varchar(255) not null,
    primary key(id)
);

DROP TABLE IF EXISTS car;
CREATE TABLE car
(
    id integer not null,
    modev varchar(255) not null,
    vendor varchar(255) not null,
    primary key(id)
);

