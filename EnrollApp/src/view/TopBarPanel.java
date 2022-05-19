package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TopBarPanel extends JPanel {

    private MainFrame mainFrame;
    public TopBarPanel(MainFrame frame) {
        mainFrame = frame;
        createView();
        setVisible(false);
    }

    private void createView(){
        add(new JLabel("Enroll app"));
        JButton subjects = new JButton("Przedmioty");

        subjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setSubjectsPanel();
            }
        });

        add(subjects);
    }
}
