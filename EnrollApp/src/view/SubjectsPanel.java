package view;

import communication.CommunicationUtil;
import hibernate_classes.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class SubjectsPanel extends JPanel {
    final private MainFrame mainFrame;

    private JList<Course> subjectsList;

    private boolean loaded = false;


    SubjectsPanel(MainFrame frame) {
        mainFrame = frame;
        createView();
    }

    private void createView() {
        setLayout(new BorderLayout());
        subjectsList = new JList<>();
        subjectsList.setFont(mainFrame.getUnifiedFont());
        subjectsList.setCellRenderer(new MyCellRenderer());

        subjectsList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = subjectsList.locationToIndex(evt.getPoint());
                    Course itemModel = subjectsList.getModel().getElementAt(index);
                    mainFrame.chooseCourse(itemModel);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(subjectsList);
        subjectsList.setLayoutOrientation(JList.VERTICAL);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void refresh() {
        if (!loaded) {
            subjectsList.setListData(CommunicationUtil.getCommunicationUtil().getCourses().toArray(new Course[0]));
            loaded = true;
        }
    }
}
