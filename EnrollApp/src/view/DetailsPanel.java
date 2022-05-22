package view;


import communication.CommunicationUtil;
import hibernate_classes.Course;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailsPanel extends JPanel {
    final private MainFrame mainFrame;

    private Course selectedCourse;

    private final JPanel infoPanel;

    private final JPanel buttonContainer;
    private JButton button;

    DetailsPanel(MainFrame frame) {
        mainFrame = frame;
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        setLayout(new GridLayout(2,0));
        add(infoPanel);
        buttonContainer = new JPanel();
        buttonContainer.setLayout(new BorderLayout());
        add(buttonContainer);
    }

    public void setCourse(Course newCourse) {
        selectedCourse = newCourse;
        updateView();
    }

    private void updateView() {
        infoPanel.removeAll();
        buttonContainer.removeAll();

        addInfo("Nazwa kursu", selectedCourse.getName());
        addInfo("Wydzial",selectedCourse.getFaculty().getName());
        addInfo("Wykladowca",selectedCourse.getLecturer().toString());
        addInfo("Opis", selectedCourse.getDescription());
        boolean isEnrolled = CommunicationUtil.getCommunicationUtil().isEnrolled(selectedCourse);

        String weekDayName = switch (selectedCourse.getWeekDay()) {
            case 0 -> "Poniedzialek";
            case 1 -> "Wtorek";
            case 2 -> "Sroda";
            case 3 -> "Czwartek";
            case 4 -> "Piatek";
            case 5 -> "Sobota";
            case 6 -> "Niedziela";
            default -> throw new IllegalArgumentException("Nieznany dzien tygodnia");
        };

        addInfo("Dzien tygodnia", weekDayName);
        addInfo("Liczba wszystkich miejsc",String.valueOf(selectedCourse.getNumberOfPlaces()));
        addInfo("Liczba wolnych miejsc",String.valueOf(CommunicationUtil.getCommunicationUtil().getNumberOfAvailablePlaces(selectedCourse)));
        addInfo("Godzina rozpoczecia",String.valueOf(selectedCourse.getStartTime()));
        addInfo("Godzina zakonczenia",String.valueOf(selectedCourse.getEndTimeTime()));
        addInfo("Czy zapisany", String.valueOf(isEnrolled));

        button = new JButton();
        button.setFont(mainFrame.getUnifiedFont());
        buttonContainer.add(button,BorderLayout.CENTER);
        if(isEnrolled){
            button.setText("Wypisz sie");
            button.setEnabled(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    CommunicationUtil.getCommunicationUtil().unroll(selectedCourse);
                    updateView();
                }
            });
        } else if (CommunicationUtil.getCommunicationUtil().getNumberOfAvailablePlaces(selectedCourse) == 0) {
            button.setText("Zapisz sie");
            button.setEnabled(false);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(mainFrame,
                            "Brak wolnych miejsc",
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            });

        } else{
            button.setText("Zapisz sie");
            button.setEnabled(true);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try
                    {
                        CommunicationUtil.getCommunicationUtil().enroll(selectedCourse);
                        updateView();
                    }catch (RuntimeException e){
                        Throwable cause = e.getCause();
                        if(cause != null) cause = cause.getCause();
                        if(cause != null){
                            JOptionPane.showMessageDialog(mainFrame,
                                    cause.getMessage().split("\n")[0],
                                    "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        else{
                            JOptionPane.showMessageDialog(mainFrame,
                                    e.getMessage().split("\n")[0],
                                    "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }

                    }
                }
            });

        }
    }

    private void addInfo(String propertyName, String value) {

        JPanel infoLabelPanel = new JPanel(new BorderLayout(10, 2));
        JLabel propName = new JLabel(propertyName + ":");
        propName.setFont(mainFrame.getUnifiedFont());
        infoLabelPanel.add(propName, BorderLayout.LINE_START);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(mainFrame.getUnifiedFont());
        infoLabelPanel.add(valueLabel, BorderLayout.CENTER);

        infoPanel.add(infoLabelPanel);
    }
}
