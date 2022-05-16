
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


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
        communicationUtil.login(100123);
//        Students student = session.get(Students.class,100123);
//        System.out.println(student.getFirstName());
        tx.commit();
        try {
        } finally {
            session.close();
        }
    }
}