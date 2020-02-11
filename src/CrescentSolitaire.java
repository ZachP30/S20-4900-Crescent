import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CrescentSolitaire {
    private JPanel CrescentSolitaire;
    private JButton helpButton;
    private JLabel drag_test;

    private static int[] card_size = new int[]{100, 136};

    public CrescentSolitaire() {
        helpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Help helpInstance = new Help();
                helpInstance.createWindow(1);
            }
        });
        drag_test.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                final Point mousePos = CrescentSolitaire.getMousePosition();
                super.mouseDragged(e);
                int x = mousePos.x;
                int y = mousePos.y;
                drag_test.setLocation(x - (card_size[0] / 2), y - (card_size[1] / 2));
            }
        });
    }

    private void buildUI(){
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/logo.png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_DEFAULT));
        drag_test.setIcon(imageIcon);
        drag_test.validate();
    }

    public void createWindow(){
        CrescentSolitaire crescentWindow = new CrescentSolitaire();
        crescentWindow.buildUI();

        JFrame frame = new JFrame("Crescent Solitaire");
        frame.setContentPane(crescentWindow.CrescentSolitaire);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
