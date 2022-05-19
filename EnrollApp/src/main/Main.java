package main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import hibernate_classes.Course;
import view.MainFrame;

import javax.swing.*;
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


        communication.CommunicationUtil communicationUtil = communication.CommunicationUtil.getCommunicationUtil(session);
        MainFrame mainFrame = new MainFrame();
//
//        communicationUtil.login(100124);
//
//        List<Course> availableCourses = communicationUtil.getCourses();
//        for(Course course : availableCourses){
//            System.out.println(course.getName() + " is enrolled :" + communicationUtil.isEnrolled(course));
//            if(course.getName().equals("test")){
//                communicationUtil.unenroll(course);
//            }
//        }
//        try {
//        } finally {
//            session.close();
//        }
    }
}