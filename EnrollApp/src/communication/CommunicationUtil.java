package communication;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import hibernate_classes.Course;
import hibernate_classes.Student;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.util.List;


public class CommunicationUtil {


    private static final SessionFactory ourSessionFactory;
    private static Session session = null;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        if(session == null){

            System.out.println();
            System.out.println("OPENING NEW SESSION");
            System.out.println();
            return ourSessionFactory.openSession();
        }else if(!session.isOpen()){

            System.out.println("SESSION WAS CLOSED, OPENING NEW SESSION");
            return ourSessionFactory.openSession();
        }

        return session;
    }

    private static CommunicationUtil communicationUtil = null;
    private static boolean logged = false;
    private static Student loggedStudent;

    private CommunicationUtil() {
    }

    public static CommunicationUtil getCommunicationUtil() {
        if (communicationUtil == null) {
            communicationUtil = new CommunicationUtil();
        }
        return communicationUtil;
    }
//    public static CommunicationUtil getCommunicationUtil(){
//        if (communicationUtil == null)
//            throw new IllegalStateException("CommunicationUnil not yet initialized");
//        return communicationUtil;
//
//    }

    public void login(int indexNumber) {
        Transaction tx = null;

        try {
            session = getSession();
            tx = session.beginTransaction();
            tx.setTimeout(5);

            StoredProcedureQuery q = session.createStoredProcedureQuery("contains_student_id")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .setParameter(1, indexNumber);
            logged = (boolean) q.getSingleResult();
            if (logged) {
                TypedQuery<Student> q2 = session.createQuery("from hibernate_classes.Student as student where student.id = :id", Student.class);
                q2.setParameter("id", indexNumber);
                loggedStudent = q2.getSingleResult();
                System.out.println("successfully logged in!");
                System.out.println("Hello " + loggedStudent.getFirstName());
            } else {
                tx.commit();
                throw new IllegalArgumentException("There is no student with this IndexNumber");
            }
            tx.commit();


        } catch (RuntimeException e) {

            if(e.getClass() == IllegalArgumentException.class)throw new IllegalArgumentException("There is no student with this IndexNumber");
            try {
                if (tx != null) tx.rollback();
            } catch (RuntimeException rbe) {
                System.out.println("Couldn’t roll back transaction");
            }
            if(session != null)session.close();
        }


    }

    public List<Course> getCourses() {

        Transaction tx = null;
        List<Course> courses = null;

        try {
            session = getSession();

            tx = session.beginTransaction();
            tx.setTimeout(5);

            StoredProcedureQuery q = session.createStoredProcedureQuery("get_courses", Course.class)
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .setParameter(1, loggedStudent.getStudentId());
            courses = q.getResultList();
            tx.commit();


        } catch (RuntimeException e) {
            try {
                if (tx != null) tx.rollback();
            } catch (RuntimeException rbe) {
                System.out.println("Couldn’t roll back transaction");
            }
            if(session != null)session.close();
        }


        return courses;
    }

    public Student getLoggedStudent() {
        if (loggedStudent == null) {
            throw new IllegalStateException("No logged in student");
        }
        return loggedStudent;
    }

    public short getNumberOfAvailablePlaces(Course course){
        Transaction tx = null;
        short result = 0;
        try{
            session = getSession();

            tx = session.beginTransaction();
            tx.setTimeout(5);
            StoredProcedureQuery q = session.createStoredProcedureQuery("no_available_places")
                    .registerStoredProcedureParameter(1,Short.class,ParameterMode.IN)
                    .setParameter(1,course.getCourseId());

            result = (short) q.getSingleResult();
            tx.commit();
        }catch (RuntimeException e) {
            try {
                if (tx != null) tx.rollback();
                System.out.println(e.getMessage());
            } catch (RuntimeException rbe) {
                System.out.println("Couldn’t roll back transaction");
            }
            if(session != null)session.close();
        }
        return result;
    }


    public boolean isEnrolled(Course course) {

        Transaction tx = null;
        boolean result = false;

        try {
            session = getSession();

            tx = session.beginTransaction();
            tx.setTimeout(5);

            StoredProcedureQuery q = session.createStoredProcedureQuery("is_student_enrolled")
                    .registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter(2, Short.class, ParameterMode.IN)
                    .setParameter(1, loggedStudent.getStudentId())
                    .setParameter(2, course.getCourseId());

            result = (boolean) q.getSingleResult();
            tx.commit();

        } catch (RuntimeException e) {
            try {
                if (tx != null) tx.rollback();
                System.out.println(e.getMessage());
            } catch (RuntimeException rbe) {
                System.out.println("Couldn’t roll back transaction");
            }
            if(session != null)session.close();
        }
        return result;

    }


    public void enroll(Course course) {
        if (!isEnrolled(course)) {

            Transaction tx = null;

            try {

                session = getSession();
                tx = session.beginTransaction();
                tx.setTimeout(10);

                loggedStudent.enroll(course);
                course.addStudent(loggedStudent);
                session.update(loggedStudent);
                session.update(course);

                tx.commit();


            } catch (RuntimeException e) {
                try {
                    loggedStudent.getCourses().remove(course);
                    course.getStudents().remove(loggedStudent);
                    session.update(loggedStudent);
                    session.update(course);
                    if (tx != null) tx.rollback();
                } catch (RuntimeException rbe) {
                    System.out.println(rbe.getMessage());

                    System.out.println("cant rollback");
                }

                if(session != null)session.close();
                throw e;
            }

        }

    }

    public void unroll(Course course) {
        if (isEnrolled(course)) {

            Transaction tx = null;

            try {
                session = getSession();

                tx = session.beginTransaction();
                tx.setTimeout(5);

                loggedStudent.getCourses().remove(course);
                course.getStudents().remove(loggedStudent);
                session.update(loggedStudent);
                session.update(course);
                tx.commit();

            } catch (RuntimeException e) {
                try {
                    System.out.println(e.getMessage());

                    if (tx != null) tx.rollback();
                } catch (RuntimeException rbe) {
                    System.out.println("Couldn’t roll back transaction");
                }
                if(session != null)session.close();
            }
        }


    }
}
