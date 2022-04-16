
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

