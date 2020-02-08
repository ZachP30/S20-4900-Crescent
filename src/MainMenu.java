import javax.swing.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {
    private JPanel mainMenuLogo;
    private JToolBar gameSelectToolbar;
    private JComboBox gameSelect;
    private JToolBar buttonToolbar;
    private JButton startGameButton;
    private JButton optionsButton;
    private JButton helpButton;
    private JPanel MainMenu;
    private JLabel logoLabel;

    public MainMenu() {
        startGameButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (gameSelect.getSelectedIndex() == 1){
                    CrescentSolitaire gameInstance = new CrescentSolitaire();
                    gameInstance.createWindow();
                }
            }
        });
        optionsButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                OptionsWindow optionsWindowInstance = new OptionsWindow();
                optionsWindowInstance.createWindow();
            }
        });
    }

    private void buildUI(){
        gameSelect.addItem("Team 1 Game");
        gameSelect.addItem("Crescent");

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/logo.png").getImage().getScaledInstance(200, 544 / 2, Image.SCALE_DEFAULT));
        logoLabel.setIcon(imageIcon);
        logoLabel.validate();
    }

    public static void main(String[] args) {
        MainMenu menuWindow = new MainMenu();
        menuWindow.buildUI();

        JFrame frame = new JFrame("Main Menu");
        frame.setContentPane(menuWindow.MainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
