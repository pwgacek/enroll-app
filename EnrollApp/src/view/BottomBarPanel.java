package view;

import hibernate_classes.Student;

import javax.swing.*;

public class BottomBarPanel extends JPanel {

    private MainFrame mainFrame;

    private Student user;

    BottomBarPanel(MainFrame frame){
        mainFrame = frame;
        setVisible(false);
    }

    public void setUser(Student newUser){
        user = newUser;
        createView();
        setVisible(true);
    }
    private void createView(){
        add(new JLabel("Witaj " + user.getFirstName()));
    }
}
