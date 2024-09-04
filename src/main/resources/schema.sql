CREATE TABLE professor(
    id INT primary KEY AUTO_INCREMENT,
    name Varchar(250),
    department Varchar(250)
);

CREATE TABLE course(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name Varchar(250);
    credits INT,
    professorid INT,
    FOREIGN KEY (professorid) REFERENCES professor(id);
);

CREATE TABLE student(
    id INT PRIMARY KEY AUTO_INCREMENT,
    name Varchar(250),
    email Varchar(250)
);

CREATE TABLE course_student (
    courseid INT PRIMARY KEY,
    studentid INT PRIMARY KEY,
    FOREIGN KEY (courseid) REFERENCES course(id)
    FOREIGN KEY (studentid) REFERENCES student(id),
    
);