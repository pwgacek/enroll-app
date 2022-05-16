
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
