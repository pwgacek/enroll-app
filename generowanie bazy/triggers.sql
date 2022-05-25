
create trigger enroll_trigger before insert
on students_courses for each  row
    execute procedure enroll_trigger_function();