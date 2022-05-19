package communication;

import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate_classes.Course;
import hibernate_classes.Student;

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
    public static CommunicationUtil getCommunicationUtil(){
        if (communicationUtil == null)
            throw new IllegalStateException("CommunicationUnil not yet initialized");
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
        Transaction tx = session.beginTransaction();
        StoredProcedureQuery q = session.createStoredProcedureQuery("contains_student_id")
                .registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN)
                .setParameter(1,indexNumber);
        logged = (boolean) q.getSingleResult();
        if(logged){
            TypedQuery<Student> q2 = session.createQuery("from hibernate_classes.Student as student where student.id = :id", Student.class);
            q2.setParameter("id",indexNumber);
            loggedStudent = q2.getSingleResult();
            System.out.println("successfully logged in!");
            System.out.println("Hello " + loggedStudent.getFirstName());
        }
        else{
            tx.commit();
            throw new IllegalArgumentException("There is no student with this IndexNumber");
        }
        tx.commit();
    }
    public List<Course> getCourses(){
        Transaction tx = session.beginTransaction();
        StoredProcedureQuery q = session.createStoredProcedureQuery("get_courses",Course.class)
                .registerStoredProcedureParameter(1, Integer.class,ParameterMode.IN)
                .setParameter(1,loggedStudent.getStudentId());
        List <Course> courses = q.getResultList();
        tx.commit();
        return courses;
    }

    public Student getLoggedStudent(){
        if(loggedStudent == null){
            throw new IllegalStateException("No logged in student");
        }
        return loggedStudent;
    }
    public boolean isEnrolled(Course course){
        Transaction tx = session.beginTransaction();
        StoredProcedureQuery q = session.createStoredProcedureQuery("is_student_enrolled")
                .registerStoredProcedureParameter(1,Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter(2,Short.class, ParameterMode.IN)
                .setParameter(1,loggedStudent.getStudentId())
                .setParameter(2,course.getCourseId());

        boolean result = (boolean)q.getSingleResult();
        tx.commit();
        return result;
    }

    public void getException(){
        Transaction tx = session.beginTransaction();
        StoredProcedureQuery q = session.createStoredProcedureQuery("get_exception");
        try{
            q.getResultList();

        }catch (PersistenceException e){

            Throwable cause = e.getCause();
            cause = cause.getCause();
            System.out.println( "~~~~" + (cause.getMessage().split("\n"))[0]);
        }
        tx.commit();
    }

    public void enroll(Course course){
        if(!isEnrolled(course)){
            Transaction tx = session.beginTransaction();

            loggedStudent.enroll(course);
            course.addStudent(loggedStudent);
            try{
                tx.commit();

            }catch(PersistenceException e){
                Throwable cause = e.getCause();
                cause = cause.getCause();
                System.out.println( "~~~~" + (cause.getMessage().split("\n"))[0]);
            }
        }

    }
    public void unenroll(Course course){
        if(isEnrolled(course)){
            Transaction tx = session.beginTransaction();
            loggedStudent.getCourses().remove(course);
            course.getStudents().remove(loggedStudent);
            tx.commit();
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
