import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.GenericJDBCException;
import test.Course;
import test.Student;

import javax.persistence.*;
import java.util.List;


public class CommunicationUtil {
    private static  CommunicationUtil communicationUtil = null;
    private static Session session;
    private static boolean logged = false;
    private static Student loggedStudent;
    private CommunicationUtil(){}

    public static CommunicationUtil getCommunicationUtil(Session session) {
        if(communicationUtil == null){
            communicationUtil = new CommunicationUtil();
            CommunicationUtil.session = session;
        }
        return communicationUtil;
    }

//    public void printAllStudentNames(){
//        Query q = session.createQuery("from test.Student");
//        List<Student> allStudents = q.getResultList();
//        for(Student student : allStudents){
//            System.out.println(student.getFirstName());
//        }
//
//    }

    public  void login(int indexNumber){
        StoredProcedureQuery q = session.createStoredProcedureQuery("contains_student_id")
                .registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN)
                .setParameter(1,indexNumber);
        logged = (boolean) q.getSingleResult();
        if(logged){
            TypedQuery<Student> q2 = session.createQuery("from test.Student as student where student.id = '"+ indexNumber+ "'", Student.class);
            loggedStudent = q2.getSingleResult();
            System.out.println("successfully logged in!");
            System.out.println("Hello " + loggedStudent.getFirstName());
        }
        else{
            System.out.println("there is no student with this IndexNumber");
        }

    }
    public List<Course> getCourses(){
        StoredProcedureQuery q = session.createStoredProcedureQuery("get_courses",Course.class)
                .registerStoredProcedureParameter(1, Integer.class,ParameterMode.IN)
                .setParameter(1,loggedStudent.getStudentId());

        return q.getResultList();
    }

    public boolean isEnrolled(Course course){
        StoredProcedureQuery q = session.createStoredProcedureQuery("is_student_enrolled")
                .registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,Short.class, ParameterMode.IN)
                .setParameter(1,loggedStudent.getStudentId())
                .setParameter(2,course.getCourseId());

        return (boolean)q.getSingleResult();
    }

    public void getException(){
        StoredProcedureQuery q = session.createStoredProcedureQuery("get_exception");
        try{
            q.getResultList();

        }catch (PersistenceException e){

            Throwable cause = e.getCause();
            cause = cause.getCause();
            System.out.println( "~~~~" + (cause.getMessage().split("\n"))[0]);
        }

    }

    public void enroll(Course course){
        if(!isEnrolled(course)){
            loggedStudent.enroll(course);
            course.addStudent(loggedStudent);
        }
    }

//    public boolean get(){}
//
//
//    public boolean canEnroll(Course course){
//        if(loggedStudent.)
//
//    }




}
