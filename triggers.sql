CREATE or replace FUNCTION canEnroll()
    returns TRIGGER
    language plpgsql
as
$$
declare
    course "Courses"%ROWTYPE;
    student "Students"%ROWTYPE;
begin
    if availableplaces(new."CourseID") <= 0 then
        raise exception 'no places available';
    end if;

    select * into course from "Courses" c where c."CourseID" = new."CourseID";
    select * into student from "Students" s where s."StudentID" = new."StudentID";

    if course."Semester" != student."Semester" then
        raise exception 'Wrong semester';
    end if;

    if not exists(select * from "StudentsFaculties" sf where sf."StudentID" = student."StudentID" and sf."FacultyID" = course."FacultyID") then
        raise exception 'Wrong faculty';
    end if;

    return new;
end;
$$;

CREATE TRIGGER enrollTrigger
    before INSERT on "StudentsCourses"
    for each row execute procedure canEnroll();
