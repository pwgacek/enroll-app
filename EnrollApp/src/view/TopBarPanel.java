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
        JLabel title = new JLabel("Enroll app");
        title.setFont(mainFrame.getUnifiedFont());
        add(title);
        JButton subjects = new JButton("Przedmioty");
        subjects.setFont(mainFrame.getUnifiedFont());
        subjects.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setSubjectsPanel();
            }
        });

        add(subjects);
    }
}
