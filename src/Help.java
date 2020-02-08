import javax.swing.*;

public class Help {
    private JPanel Help;
    private JTextArea helpTextArea;

    public void buildUI(int gameIndex){
        helpTextArea.setText("Crescent is a solitaire card game played with two decks of playing cards mixed together. The game is so called because when the cards are dealt properly, the resulting piles should form a large arc or a crescent.\n" +
                "\n" +
                "First, one king and one ace of each suit are removed to form the bases for the foundations. The kings are placed on a row, while the aces are placed below the kings. The ninety-six remaining cards are dealt into 16 piles of six cards each, faced down. If the player chooses, the piles should form a large arc, as mentioned above. After the cards are dealt, the top card of each pile is turned face up.\n" +
                "\n" +
                "The object is to move all the cards from the semicircle tableau to the foundations. The kings are built down by suit up to aces and the aces are built up, also by suit, to kings.\n" +
                "\n" +
                "The top card of each pile in the semicircle are available to play on the foundations or around the tableau. Only one card can be moved at a time and building on the tableau is either up or down by suit and can go round-the-corner (placing a king over an ace and vice versa). Once a face-down card becomes exposed, it is turned face up. Spaces are not filled.\n" +
                "\n" +
                "When the king and ace foundations are in sequence, one can transfer the cards from one foundation to the other except the base cards.\n" +
                "\n" +
                "When all possible moves have been made—or the player has made all moves he wanted to make—a special redeal move is made. The bottom card of each pile on the semicircle is placed on the top without disturbing the order of the other cards in the pile. This can only be done three times in the entire game.\n" +
                "\n" +
                "The game is won when all 104 cards end up in the foundations.");

    }

    public void createWindow(int gameIndex){
        Help helpWindow = new Help();
        helpWindow.buildUI(gameIndex);

        JFrame frame = new JFrame("Help");
        frame.setContentPane(helpWindow.Help);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
