package view;

import communication.CommunicationUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final MainFrame mainFrame;
    private JTextField loginInput;
    private JLabel errorMessage;
    public LoginPanel(MainFrame frame){
        mainFrame = frame;
        createView();

    }

    private void createView(){
        setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 15));

        add(new JLabel(" "));
        add(new JLabel(" "));
        add(new JLabel(" "));
        add(new JLabel(" "));

        JLabel description = new JLabel();
        description.setText("Podaj indeks");
        description.setFont(mainFrame.getUnifiedFont());
        add(description);

        loginInput = new JTextField();
        loginInput.setColumns(20);  // width of input
        loginInput.setFont(mainFrame.getUnifiedFont());
        add(loginInput);

        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setFont(mainFrame.getUnifiedFont());
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        add(loginButton);

        errorMessage = new JLabel("");
//        errorMessage.setFont();
        add(errorMessage);

    }

    private void login(){
        try {
            int index = Integer.parseInt(loginInput.getText());
            CommunicationUtil.getCommunicationUtil().login(index);
            loginSuccess();
        }catch (NumberFormatException e){
            errorMessage.setText("Enter a number");
        }catch (IllegalArgumentException e){
            errorMessage.setText("User with given index not found");
        }
        // Change view - login success
    }

    private void loginSuccess(){
        mainFrame.loginSuccess();
    }
}
