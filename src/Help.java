import javax.swing.*;

public class Help {
    private JPanel Help;
    private JTextArea helpTextArea;

    public void startWindowInstance(){
        JFrame frame = new JFrame("Help");
        frame.setContentPane(new Help().Help);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
