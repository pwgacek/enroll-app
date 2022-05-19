package view;


import hibernate_classes.Course;

import javax.swing.*;

public class DetailsPanel extends JPanel {
    final private MainFrame mainFrame;

    private Course selectedCourse;
    DetailsPanel(MainFrame frame){
        mainFrame = frame;
    }

    public void setCourse(Course newCourse){
        selectedCourse = newCourse;
    }

}
