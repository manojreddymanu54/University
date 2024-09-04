INSERT INTO PROFESSOR(id,name,department) VALUES (1,John Smith,Computer Science);
INSERT INTO PROFESSOR(id,name,department) VALUES(2,Mary Johnson,Physics);
INSERT INTO PROFESSOR(id,name,department) VALUES (3,David Lee,Mathematics);



INSERT INTO COURSE(id,name,credits,professorid) VALUES (1,Introduction to Programming,3,1);
INSERT INTO COURSE(id,name,credits,professorid) VALUES(2,Quantum Mechanics,4,2);
INSERT INTO COURSE(id,name,credits,professorid) VALUES(3,Calculus,4,3);



INSERT INTO STUDENT (id,name,email) VALUES(1,Alice Johnson,alice@example.com);
INSERT INTO STUDENT (id,name,email) VALUES(2,Bob Davis,bob@example.com);
INSERT INTO STUDENT (id,name,email) VALUES(3,Eva Wilson,eva@example.com);




INSERT INTO course_student(courseid,studentid) VALUES(1,1);
INSERT INTO course_student(courseid,studentid) VALUES(1,2);
INSERT INTO course_student(courseid,studentid) VALUES(2,2);
INSERT INTO course_student(courseid,studentid) VALUES(2,3);
INSERT INTO course_student(courseid,studentid) VALUES(3,1);
INSERT INTO course_student(courseid,studentid) VALUES(3,3);