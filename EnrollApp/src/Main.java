
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import test.Course;

import java.util.List;


public class Main {
    private static final SessionFactory ourSessionFactory;

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
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {
        final Session session = getSession();
        Transaction tx = session.beginTransaction();
        CommunicationUtil communicationUtil = CommunicationUtil.getCommunicationUtil(session);
        //communicationUtil.printAllStudentNames();
        communicationUtil.login(100126);
//        Students student = session.get(Students.class,100123);
//        System.out.println(student.getFirstName());
        List<Course> availableCourses = communicationUtil.getCourses();
        for(Course course : availableCourses){
            System.out.println(course.getName() + " is enrolled :" + communicationUtil.isEnrolled(course));
            //communicationUtil.enroll(course);
        }

        //communicationUtil.getException();
        tx.commit();
        try {
        } finally {
            session.close();
        }
    }
}