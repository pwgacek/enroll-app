package hibernate_classes;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Faculties", schema = "public", catalog = "enroll_database")
public class Faculty {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    //@Column(name = "FacultyID")
    private short facultyId;
    //@Basic
    //@Column(name = "Name")
    private String name;
    @ManyToMany(mappedBy = "faculties")
    private Set<Student> students;


    public short getFacultyId() {
        return facultyId;
    }


    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty that = (Faculty) o;
        return facultyId == that.facultyId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, name);
    }


}
