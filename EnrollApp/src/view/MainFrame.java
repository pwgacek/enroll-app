package view;


import communication.CommunicationUtil;
import hibernate_classes.Course;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {

    private static final String DETAILS = "details_panel";
    private static final String LOGIN = "login_panel";
    private static final String SUBJECTS = "subjects_panel";

    private JPanel mainPanel;
    private CardLayout mainPanelLayout;
    private LoginPanel loginPanel;
    private TopBarPanel topBarPanel;
    private BottomBarPanel bottomBarPanel;
    private DetailsPanel detailsPanel;
    private SubjectsPanel subjectsPanel;
    private Font unifiedFont;

    public MainFrame() {
        setSize(500, 500);
        setTitle("Enroll");
        center();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        unifiedFont = new Font("JetBrains Mono", Font.PLAIN, 16);
        topBarPanel = new TopBarPanel(this);
        bottomBarPanel = new BottomBarPanel(this);

        mainPanel = createMainPanel();

        setLayout(new BorderLayout());
        add(topBarPanel,BorderLayout.PAGE_START);
        add(bottomBarPanel,BorderLayout.PAGE_END);
        add(mainPanel,BorderLayout.CENTER);
        setVisible(true);
//        currentPanel = new LoginPanel();
//        add(currentPanel);

    }



    private JPanel createMainPanel() {
        JPanel res = new JPanel();
        mainPanelLayout = new CardLayout();
        res.setLayout(mainPanelLayout);

        loginPanel = new LoginPanel(this);
        subjectsPanel = new SubjectsPanel(this);
        detailsPanel = new DetailsPanel(this);

        res.add(loginPanel, LOGIN);
        res.add(subjectsPanel, SUBJECTS);
        res.add(detailsPanel, DETAILS);

        mainPanelLayout.show(res, LOGIN);

        return res;
    }

    private void center() {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
    }

    public void setPanel(String panel) {
        mainPanelLayout.show(mainPanel, panel);
    }

    public void setLoginPanel() {
        topBarPanel.setVisible(false);
        setPanel(LOGIN);
    }

    public void setDetailsPanel() {
        topBarPanel.setVisible(true);
        setPanel(DETAILS);
    }

    public void setSubjectsPanel() {
        topBarPanel.setVisible(true);
        subjectsPanel.refresh();
        setPanel(SUBJECTS);
    }

    public void chooseCourse(Course itemModel) {
        topBarPanel.setVisible(true);
        setDetailsPanel();
        detailsPanel.setCourse(itemModel);
    }
    public Font getUnifiedFont() {
        return unifiedFont;
    }

    public void loginSuccess() {
        topBarPanel.setVisible(true);
        subjectsPanel.refresh();
        setPanel(SUBJECTS);
        bottomBarPanel.setUser(CommunicationUtil.getCommunicationUtil().getLoggedStudent());
    }
}
