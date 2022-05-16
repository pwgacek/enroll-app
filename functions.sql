
create or replace function availablePlaces (courseID smallint)
returns smallint
language plpgsql
as $$
DECLARE
    takenPlaces integer;
    totalPlaces integer;

Begin
    if not exists(select * from "Courses" where "CourseID" = courseid) then
        raise exception 'Course not found';
    end if;
    select c."NumberOfPlaces" into totalPlaces from "Courses" as c where c."CourseID" = courseID;

    select COUNT(*) into takenPlaces from "StudentsCourses" as sc where sc."CourseID" = CourseID;

    return totalPlaces - takenPlaces;
end
$$;

create or replace function getCourses(StudentID text)
returns table (
    "CourseID" smallint,
    "Name" text,
    "FacultyID" smallint,
    "LecturerID" smallint,
    "NumberOfPlaces" smallint,
    "ETCS" smallint,
    "Description" text
) as $$
declare
	ids smallint[];
	student "Students"%rowtype;
begin
	select  * into student from "Students" as s where StudentID = s."StudentID";
	if student is null then
        raise exception 'Student not found';
    end if;
	 select ARRAY(select sf."FacultyID" from "StudentsFaculties" as sf where sf."StudentID" = StudentID) into ids;

	return query
		select c."CourseID",c."Name",c."FacultyID",c."LecturerID",c."NumberOfPlaces",c."ETCS",c."Description" from "Courses" as c
		where c."Semester" = student."Semester" and c."FacultyID" = any(ids);
end
$$ Language plpgsql;

create or replace function contains_student_id(student_id bigint)
returns bool
language plpgsql
as
$$
declare
    student students%ROWTYPE;
BEGIN
select  * into student from students  where studentid = student_id limit 1;
if student is null THEN
    return false;
end if;
return true;

end;
$$

/* v2*/
create or replace function get_courses(student_id bigint)
returns setof public.courses as $$
declare
	ids smallint[];
	student students%rowtype;
begin
	select  * into student from students as s where student_id = s.studentid;
	if student is null then
        raise exception 'Student not found';
    end if;
	 select ARRAY(select sf.faculties_facultyid from students_faculties as sf where sf.students_studentid = student_id) into ids;

	return query
		select * from courses as c
		where c.semester = student.semester and c.faculty_facultyid = any(ids);
end
$$ Language plpgsql;


create or replace function is_student_enrolled(student_id integer, course_id integer)
returns bool as
$$
declare
    enrollment students_courses%ROWTYPE;
begin
    select * into enrollment from students_courses as sc
    where sc.students_studentid = student_id and sc.courses_courseid = course_id;

    if enrollment is null then
        return false;
    end if;
    return true;
end;
$$ Language plpgsql;


create or replace function get_used_etcs(student_id bigint)
returns Integer as
$$
    declare
        used_etcs smallint;
    begin
        if not contains_student_id(student_id) then
            raise exception 'Student not found';
        end if;
        select into used_etcs sum(c.etcs) from courses as c
            inner join students_courses sc on c.courseid = sc.courses_courseid
        where sc.students_studentid = student_id;
        if used_etcs is null then
            return 0;
        end if;
        return used_etcs;
    end;
$$ Language plpgsql;