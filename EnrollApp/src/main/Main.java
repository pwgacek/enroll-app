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

    public static void main(final String[] args) throws Exception {




        communication.CommunicationUtil communicationUtil = communication.CommunicationUtil.getCommunicationUtil();
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