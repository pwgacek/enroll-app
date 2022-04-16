
create or replace function availablePlaces (courseID smallint)
returns integer
language plpgsql
as $$
DECLARE
    takenPlaces integer;
    totalPlaces integer;

Begin
    select c."NumberOfPlaces" into totalPlaces from "Courses" as c where c."CourseID" = courseID;

    select COUNT(*) into takenPlaces from "StudentsCourses" as sc where sc."CourseID" = courseID;

    return totalPlaces - takenPlaces;
end
$$;