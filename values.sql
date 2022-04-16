begin;
insert into "Lecturers" ("FirstName", "LastName", "Degree") VALUES ('Maciej','Kowalski','Mgr');
insert into "Lecturers" ("FirstName", "LastName", "Degree") VALUES ('Jan','Nowak','Dr');
insert into "Lecturers" ("FirstName", "LastName", "Degree") VALUES ('Andrzej','Kowalski','Inz');

insert into "Faculties" ("Name") VALUES ('WIET');
insert into "Faculties" ("Name") VALUES ('WILGZ');
insert into "Faculties" ("Name") VALUES ('WIMC');

insert into "Students" ("StudentID", "FirstName", "LastName", "Pesel", "Semester") VALUES ('000123','Jan','Mirek','12345678910','3');
insert into "Students" ("StudentID", "FirstName", "LastName", "Pesel", "Semester") VALUES ('000124','Michal','Kowal','12345678911','3');
insert into "Students" ("StudentID", "FirstName", "LastName", "Pesel", "Semester") VALUES ('000125','Jakub','Mirek','12345678912','4');
insert into "Students" ("StudentID", "FirstName", "LastName", "Pesel", "Semester") VALUES ('000126','Zuzanna','Nowak','22345678910','4');
insert into "Students" ("StudentID", "FirstName", "LastName", "Pesel", "Semester") VALUES ('000127','Jagoda','Nowakowska','22345678911','1');
insert into "Students" ("StudentID", "FirstName", "LastName", "Pesel", "Semester") VALUES ('000128','Agnieszka','Szuwarek','22345678912','5');

insert into "Courses" ("Name", "FacultyID", "LecturerID", "NumberOfPlaces", "ETCS", "Description", "Semester") VALUES
('Bazy Danych',1,1,10,3,'Przedmiot o bazach danych',4);
insert into "Courses" ("Name", "FacultyID", "LecturerID", "NumberOfPlaces", "ETCS", "Description", "Semester") VALUES
('Bazy Danych',1,1,3,3,'Przedmiot o bazach danych',4);
insert into "Courses" ("Name", "FacultyID", "LecturerID", "NumberOfPlaces", "ETCS", "Description", "Semester") VALUES
('Systemy Operacyjne',1,1,10,4,'Przedmiot o Systemach operacyjnych',3);
insert into "Courses" ("Name", "FacultyID", "LecturerID", "NumberOfPlaces", "ETCS", "Description", "Semester") VALUES
('Matematyka',2,1,3,3,NULL,4);

insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000123',1);
insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000123',2);
insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000124',1);
insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000125',1);
insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000126',1);
insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000127',1);
insert into "StudentsFaculties" ("StudentID", "FacultyID") VALUES ('000128',1);

insert into "StudentsCourses" ("StudentID", "CourseID") VALUES ('000123',3);
insert into "StudentsCourses" ("StudentID", "CourseID") VALUES ('000125',1);
insert into "StudentsCourses" ("StudentID", "CourseID") VALUES ('000126',1);

end;

