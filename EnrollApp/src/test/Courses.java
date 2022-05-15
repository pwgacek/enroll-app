package test;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
//@Table(name = "Courses", schema = "public", catalog = "db2-project")
public class Courses {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
//    @Column(name = "CourseID")
    private short courseId;
//    @Basic
//    @Column(name = "Name")
    private String name;

//    @Basic
//    @Column(name = "NumberOfPlaces")
    private short numberOfPlaces;
//    @Basic
//    @Column(name = "ETCS")
    private short etcs;
//    @Basic
//    @Column(name = "Description")
    private String description;
//    @Basic
//    @Column(name = "Semester")
    private short semester;

    @ManyToOne
    private Faculties faculty;
    @ManyToOne
    private Lecturers lecturer;

    @ManyToMany(mappedBy = "courses")
    private Set<Students> students;
    public short getCourseId() {
        return courseId;
    }

    public void setCourseId(short courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(short numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public short getEtcs() {
        return etcs;
    }

    public void setEtcs(short etcs) {
        this.etcs = etcs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getSemester() {
        return semester;
    }

    public void setSemester(short semester) {
        this.semester = semester;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Courses that = (Courses) o;
        return courseId == that.courseId && numberOfPlaces == that.numberOfPlaces && etcs == that.etcs && semester == that.semester && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, name, numberOfPlaces, etcs, description, semester);
    }
}
