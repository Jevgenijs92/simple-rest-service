CREATE TABLE hibernate_sequence
(
    next_val BIGINT
);

INSERT INTO hibernate_sequence
VALUES (1);

CREATE SEQUENCE hibernate_sequence;

CREATE TABLE person (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    personal_id VARCHAR(14) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender TINYINT NOT NULL,
    birthdate DATE NOT NULL
);

INSERT INTO person (personal_id, first_name, last_name, gender, birthdate)
VALUES ('25031982-35985', 'Eleni', 'Lawson', 2, DATE'1982-03-25'),
       ('16111994-54150', 'Cherie', 'Whittaker', 2, DATE'1994-11-16'),
       ('14041993-61706', 'Ella-Rose', 'Petersen', 2, DATE'1993-04-14'),
       ('12061989-84281', 'Mohamad', 'Cowan', 1, DATE'1989-06-12'),
       ('05101992-71248', 'Leona', 'Vo', 2, DATE'1992-10-05'),
       ('20081995-54030', 'Aadil', 'Christensen', 1, DATE'1995-08-20'),
       ('10091991-96448', 'Ivy-Rose', 'Kidd', 2, DATE'1991-09-10'),
       ('16091993-68873', 'Zayaan', 'Frye', 1, DATE'1993-09-16'),
       ('24111995-55042', 'Gabriel', 'Washington', 1, DATE'1995-11-24'),
       ('25061998-78181', 'Aarush', 'Neale', 1, DATE'1998-06-25'),
       ('05051992-71214', 'Anayah', 'Clegg', 1, DATE'1992-05-05'),
       ('31071988-64184', 'Ayesha', 'Kendall', 2, DATE'1988-07-31'),
       ('11061990-17332', 'Clarissa', 'Green', 2, DATE'1990-06-11'),
       ('25071982-57861', 'Efa', 'Mcarthur', 2, DATE'1982-07-25'),
       ('12011988-75774', 'Armani', 'Needham', 1, DATE'1988-01-12'),
       ('04121982-81305', 'Mari', 'Holmes', 2, DATE'1982-12-04'),
       ('17101982-50516', 'Monty', 'Ortega', 2, DATE'1982-10-17'),
       ('10091986-78271', 'June', 'Field', 1, DATE'1986-09-10'),
       ('31031993-25177', 'Lacey', 'Curry', 2, DATE'1993-03-31'),
       ('24111996-81392', 'Eesha', 'Byrne', 2, DATE'1996-11-24'),
       ('26071982-66451', 'Niam', 'Gentry', 1, DATE'1982-07-26'),
       ('05011982-82778', 'Abu', 'Shannon', 1, DATE'1982-01-05'),
       ('04061998-47027', 'Krystian', 'Klein', 1, DATE'1998-06-04'),
       ('19041981-80660', 'Reuben', 'Buck', 1, DATE'1981-04-19'),
       ('24031985-90444', 'Kaiden', 'Kim', 1, DATE'1985-03-24'),
       ('11021984-96680', 'Brax', 'Macdonald', 1, DATE'1984-02-11'),
       ('26091989-62395', 'Lorcan', 'Croft', 1, DATE'1989-09-26'),
       ('31081987-22692', 'Jia', 'Clifford', 2, DATE'1987-08-31'),
       ('14021999-26402', 'Alaw', 'Milner', 1, DATE'1999-02-14'),
       ('21031995-82441', 'Carolyn', 'Appleton', 2, DATE'1995-03-21'),
       ('17011985-68544', 'Dru', 'Larsen', 1, DATE'1985-01-17'),
       ('27041998-60900', 'Steffan', 'Sanchez', 1, DATE'1998-04-27'),
       ('21091983-39297', 'Cerys', 'Salgado', 1, DATE'1983-09-21'),
       ('18121985-24720', 'Aasiyah', 'English', 1, DATE'1985-12-18'),
       ('17061994-59221', 'Adelle', 'Campbell', 2, DATE'1994-06-17'),
       ('24111989-44076', 'Karolina', 'Diaz', 2, DATE'1989-11-24'),
       ('17011994-68914', 'Adnan', 'Deacon', 2, DATE'1994-01-17'),
       ('17051998-72095', 'Theia', 'Benitez', 2, DATE'1998-05-17'),
       ('08071984-36931', 'Fallon', 'Herrera', 2, DATE'1984-07-08'),
       ('12121987-75484', 'Donell', 'Forrest', 1, DATE'1987-12-12'),
       ('13011984-64029', 'Reeva', 'Andrews', 2, DATE'1984-01-13'),
       ('25121996-83930', 'Alfred', 'Dodd', 1, DATE'1996-12-25'),
       ('02061992-89836', 'Vernon', 'Mason', 1, DATE'1992-06-02'),
       ('14061989-48978', 'Devon', 'Reynolds', 1, DATE'1989-06-14'),
       ('26121997-39014', 'Georgia', 'Vang', 2, DATE'1997-12-26'),
       ('24081983-11294', 'Ronny', 'Benton', 1, DATE'1983-08-24'),
       ('07051996-75705', 'Willem', 'Li', 1, DATE'1996-05-07'),
       ('08111981-95743', 'Cohen', 'Haley', 1, DATE'1981-11-08'),
       ('22121990-95568', 'Celine', 'Stephenson', 2, DATE'1990-12-22'),
       ('12081983-59844', 'Samad', 'Sears', 1, DATE'1983-08-12');