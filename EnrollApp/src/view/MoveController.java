package view;

public class MoveController {
    private MainFrame mainFrame;

    public void setMainFrame(MainFrame frame){
        mainFrame = frame;
    }

    public void loginSuccess(){
        mainFrame.setSubjectsPanel();
    }
}
