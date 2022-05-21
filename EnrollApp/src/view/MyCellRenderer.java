package view;

import communication.CommunicationUtil;
import hibernate_classes.Course;

import javax.swing.*;
import java.awt.*;

public class MyCellRenderer extends JLabel implements ListCellRenderer<Course> {

    public MyCellRenderer(){
        setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Course> list, Course value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.toString());

        boolean isEnrolled = CommunicationUtil.getCommunicationUtil().isEnrolled(value);
        boolean availablePlaces = CommunicationUtil.getCommunicationUtil().getNumberOfAvailablePlaces(value) > 0;

        if (isEnrolled && isSelected){
            setBackground(new Color(0,150,0));
        } else if (isEnrolled) {
            setBackground(new Color(0,255,0));
        }else if (isSelected && availablePlaces){
            setBackground(new Color(222,222,222));
        }
        else if (!isSelected && availablePlaces){
            setBackground(new Color(255,255,255));
        }
        else if(isSelected){    // brak miejsc
            setBackground(new Color(200,0,0));
        }
        else{
            setBackground(new Color(255,0,0));
        }
        setForeground(new Color(0,0,0));
        return this;
    }
}
