import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CrescentSolitaire {
    private JPanel CrescentSolitaire;
    private JButton helpButton;

    public CrescentSolitaire() {
        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Help helpInstance = new Help();
                helpInstance.createWindow(1);
            }
        });
    }

    public void createWindow(){
        JFrame frame = new JFrame("Crescent Solitaire");
        frame.setContentPane(new CrescentSolitaire().CrescentSolitaire);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
