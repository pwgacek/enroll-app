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
        JLabel welcomeText = new JLabel("Witaj " + user.getFirstName());
        welcomeText.setFont(mainFrame.getUnifiedFont());
        add(welcomeText);
    }
}
