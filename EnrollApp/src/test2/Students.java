package test2;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Students", schema = "public", catalog = "db2-project")
public class Students {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "StudentID")
    private int studentId;
    @Basic
    @Column(name = "FirstName")
    private String firstName;
    @Basic
    @Column(name = "LastName")
    private String lastName;
    @Basic
    @Column(name = "Pesel")
    private String pesel;
    @Basic
    @Column(name = "Semester")
    private short semester;
    @ManyToMany
     Set<Courses> courses;
    @ManyToMany
     Set<Faculties> faculties;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
        Students that = (Students) o;
        return semester == that.semester && Objects.equals(studentId, that.studentId) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(pesel, that.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, firstName, lastName, pesel, semester);
    }

}
