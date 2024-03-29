begin;
insert into "lecturers" ("firstname", "lastname", "degree") VALUES ('Maciej','Kowalski','Mgr');
insert into "lecturers" ("firstname", "lastname", "degree") VALUES ('Jan','Nowak','Dr');
insert into "lecturers" ("firstname", "lastname", "degree") VALUES ('Andrzej','Kowalski','Inz');

insert into "faculties" ("name") VALUES ('WIET');
insert into "faculties" ("name") VALUES ('WILGZ');
insert into "faculties" ("name") VALUES ('WIMC');

insert into "students" ("studentid", "firstname", "lastname", "pesel", "semester") VALUES (100123,'Jan','Mirek','12345678910','3');
insert into "students" ("studentid", "firstname", "lastname", "pesel", "semester")  VALUES (100124,'Michal','Kowal','12345678911','3');
insert into "students" ("studentid", "firstname", "lastname", "pesel", "semester")  VALUES (100125,'Jakub','Mirek','12345678912','4');
insert into "students" ("studentid", "firstname", "lastname", "pesel", "semester")  VALUES (100126,'Zuzanna','Nowak','22345678910','4');
insert into "students" ("studentid", "firstname", "lastname", "pesel", "semester")  VALUES (100127,'Jagoda','Nowakowska','22345678911','1');
insert into "students" ("studentid", "firstname", "lastname", "pesel", "semester")  VALUES (100128,'Agnieszka','Szuwarek','22345678912','3');
/*
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Bazy Danych',1,1,10,3,'Przedmiot o bazach danych',4,1,'12:50:00','14:20:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Bazy Danych',1,1,3,3,'Przedmiot o bazach danych',4,2,'14:40:00','16:10:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Systemy Operacyjne',1,1,10,4,'Przedmiot o Systemach operacyjnych',3,3,'16:15:00','17:45:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Matematyka',2,1,3,3,NULL,4,2,'12:50:00','14:20:00');
*/
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Programowanie Funkcyjne',1,2,10,3,'Podstawy paradygmatu funkcyjnego',3,3,'12:50:00','14:20:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Rownania Rozniczkowe',1,1,3,4,'Elementy teorii rownan rozniczkowych',3,3,'12:50:00','14:20:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Statystyka',1,3,2,3,'Podstawy rachunku prawdopodobienstwa',3,1,'16:15:00','17:45:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Fizyka 2',1,2,10,25,'Zawaansowane zagadniena z fizyki',3,4,'16:15:00','17:45:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Matematyka',2,1,3,4,'Anilza matematyczna, matematyka dyskretna',3,5,'12:50:00','14:20:00');
insert into "courses" ("name", "faculty_facultyid", "lecturer_lecturerid", "numberofplaces", "etcs", "description", "semester","weekday","starttime","endtime") VALUES
    ('Systemy Operacyjne',1,1,3,5,'Zasady funkcjonowania systemów operacyjnych',4,5,'12:50:00','14:20:00');

insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100123,1);
insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100123,2);
insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100124,1);
insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100125,1);
insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100126,1);
insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100127,1);
insert into "students_faculties" ("students_studentid", "faculties_facultyid") VALUES (100128,1);


end;
