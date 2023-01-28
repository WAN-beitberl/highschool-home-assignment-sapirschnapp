create database sapir;   
USE sapir;
CREATE TABLE highschool ( 
    Id INT PRIMARY KEY,
    First_name VARCHAR(50) NOT NULL,
    Last_name VARCHAR(50) NOT NULL,
    Email VARCHAR(50),
    Gender VARCHAR(20),
    Ip_address VARCHAR(50),
    Height FLOAT,
    Age INT CHECK (AGE > 0),
    Has_car VARCHAR(50) ,
    Car_color VARCHAR(20) ,
    Grade INT,
    Grade_avg FLOAT,
    Identification_card VARCHAR(50) UNIQUE
  CONSTRAINT `highschool_chk_1` CHECK ((`AGE` > 0)),
  CONSTRAINT `highschool_chk_2` CHECK ((`Has_car` in ('True','False'))),
  CONSTRAINT `highschool_chk_3` CHECK ((`Grade` between 1 and 12)),
  CONSTRAINT `highschool_chk_4` CHECK ((`Grade_avg` between 0 and 100))
);
select * from highschool;

CREATE TABLE friendships(
id INT PRIMARY KEY NOT NULL,
friend_id INT FOREIGN KEY REFERENCES highschool(Id),
other_friend_id INT FOREIGN KEY REFERENCES highschool(Id)
);
select * from friendships;
