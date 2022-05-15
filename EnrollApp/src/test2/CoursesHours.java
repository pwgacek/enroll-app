package test2;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

@Embeddable
class CoursesHoursId implements Serializable {
    private short courseId;
    private short weekDay;
    private Time startTime;

    public short getCourseId() {
        return courseId;
    }

    public void setCourseId(short courseId) {
        this.courseId = courseId;
    }

    public short getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(short weekDay) {
        this.weekDay = weekDay;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
}
@Entity
@Table(name = "CoursesHours", schema = "public", catalog = "db2-project")
public class CoursesHours {

    @EmbeddedId CoursesHoursId id;
    @Basic
    @Column(name = "EndTime")
    private Time endTime;

    @MapsId("courseId")
    @OneToOne
    Courses course;


    public short getCourseId() {
        return id.getCourseId();
    }

    public void setCourseId(short courseId) {
        this.id.setCourseId(courseId);
    }

    public short getWeekDay() {
        return id.getWeekDay();
    }

    public void setWeekDay(short weekDay) {
        this.id.setWeekDay(weekDay);
    }

    public Time getStartTime() {
        return id.getStartTime();
    }

    public void setStartTime(Time startTime) {
        id.setStartTime(startTime);
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesHours that = (CoursesHours) o;
        return id.getCourseId() == that.id.getCourseId() && id.getWeekDay() == that.id.getWeekDay() && Objects.equals(id.getStartTime(), that.id.getStartTime()) && Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getCourseId(), id.getStartTime(), id.getWeekDay(), endTime);
    }

}
