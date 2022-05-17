package hibernate_classes;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Lecturers", schema = "public", catalog = "projekt")
public class Lecturer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
//    @Column(name = "LecturerID")
    private short lecturerId;
//    @Basic
//    @Column(name = "FirstName")
    private String firstName;
//    @Basic
//    @Column(name = "LastName")
    private String lastName;
//    @Basic
//    @Column(name = "Degree")
    private String degree;


    public short getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(short lecturerId) {
        this.lecturerId = lecturerId;
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

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer that = (Lecturer) o;
        return lecturerId == that.lecturerId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lecturerId, firstName, lastName, degree);
    }


}
