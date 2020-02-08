import javax.swing.*;

public class OptionsWindow {
    private JPanel OptionsWindow;

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("OptionsWindow");
//        frame.setContentPane(new OptionsWindow().OptionsWindow);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//    }

    public void createWindow(){
        JFrame frame = new JFrame("OptionsWindow");
        frame.setContentPane(new OptionsWindow().OptionsWindow);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
