package test;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
//@Table(name = "Faculties", schema = "public", catalog = "db2-project")
public class Faculties {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    //@Column(name = "FacultyID")
    private short facultyId;
    //@Basic
    //@Column(name = "Name")
    private String name;
    @ManyToMany(mappedBy = "faculties")
    private Set<Students> students;


    public short getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(short facultyId) {
        this.facultyId = facultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculties that = (Faculties) o;
        return facultyId == that.facultyId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, name);
    }


}
