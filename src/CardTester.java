import java.awt.event.*;
import java.util.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.Point;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardTester {
    public static int hoveredInnerCardStack = -1;
    public static void main(String[] args) {
        int[] card_size = new int[]{100, 136};
        int[] windowSize = new int[]{1200, 800};
        Card draggedCard = new Card();
        JPanel CrescentSolitaire = new JPanel();
        CrescentSolitaire.setLayout(null);
        String[] suitList = new String[]{
                "C",
                "S",
                "H",
                "D"
        };

        int[] valueList = new int[]{
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13
        };

        ArrayList<String> scoreTracker = new ArrayList<String>();
        String playerName = "Dax"; //filelauncher.getUsername()
        int score = 0;
        scoreTracker.add(Integer.toString(score) + "," + playerName);
        System.out.println(scoreTracker.get(0));

        //Decks
        ArrayList<Card> deck = new ArrayList<Card>(96);


        for (int i = 0; i < suitList.length; i++) {
            for (int j = 0; j < valueList.length - 1; j++) { //leaves out kings
                Card myCard = new Card(); // create new card with defaults
                myCard.setValue(valueList[j]);
                myCard.setSuit(suitList[i]);
                myCard.setType("sextet");
                deck.add(myCard); // add card to deck
            }
        }

        for (int i = 0; i < suitList.length; i++) {
            for (int j = 1; j < valueList.length; j++) { //leaves out aces
                Card myCard = new Card(); // create new card with defaults
                myCard.setValue(valueList[j]);
                myCard.setSuit(suitList[i]);
                myCard.setType("sextet");
                deck.add(myCard);
            }
        }

//        Collections.shuffle(deck);

        //Piles of outside cards
        ArrayList<OuterCardStack> outsideSextets = new ArrayList<OuterCardStack>();
        ArrayList<InnerCardStack> innerStacks = new ArrayList<InnerCardStack>();

        Point[] outsideSextetLocations = new Point[]{
                new Point((windowSize[0] / 8) - card_size[0], (windowSize[1] / 5) * 5 - card_size[1]),
                new Point((windowSize[0] / 8) - card_size[0], (windowSize[1] / 5) * 4 - card_size[1]),
                new Point((windowSize[0] / 8) - card_size[0], (windowSize[1] / 5) * 3 - card_size[1]),
                new Point((windowSize[0] / 8) - card_size[0], (windowSize[1] / 5) * 2 - card_size[1]),
                new Point((windowSize[0] / 8) - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 2 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 3 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 4 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 5 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 6 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 7 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 8 - card_size[0], (windowSize[1] / 5) - card_size[1]),
                new Point((windowSize[0] / 8) * 8 - card_size[0], (windowSize[1] / 5) * 2 - card_size[1]),
                new Point((windowSize[0] / 8) * 8 - card_size[0], (windowSize[1] / 5) * 3 - card_size[1]),
                new Point((windowSize[0] / 8) * 8 - card_size[0], (windowSize[1] / 5) * 4 - card_size[1]),
                new Point((windowSize[0] / 8) * 8 - card_size[0], (windowSize[1] / 5) * 5 - card_size[1]),
        };

        Point[] innerStackLocations = new Point[]{
                new Point((windowSize[0] / 8) * 3 - card_size[0], (windowSize[1] / 5) * 7 - card_size[1]),
                new Point((windowSize[0] / 8) * 4 - card_size[0], (windowSize[1] / 5) * 7 - card_size[1]),
                new Point((windowSize[0] / 8) * 5 - card_size[0], (windowSize[1] / 5) * 7 - card_size[1]),
                new Point((windowSize[0] / 8) * 6 - card_size[0], (windowSize[1] / 5) * 7 - card_size[1]),
                new Point((windowSize[0] / 8) * 3 - card_size[0], (windowSize[1] / 5) * 8 - card_size[1]),
                new Point((windowSize[0] / 8) * 4 - card_size[0], (windowSize[1] / 5) * 8 - card_size[1]),
                new Point((windowSize[0] / 8) * 5 - card_size[0], (windowSize[1] / 5) * 8 - card_size[1]),
                new Point((windowSize[0] / 8) * 6 - card_size[0], (windowSize[1] / 5) * 8 - card_size[1]),
        };

        for (int i = 0; i < 16; i++) {
            ArrayList<Card> internalStack = new ArrayList<Card>();
            for (int j = 0; j < 6; j++) {
                internalStack.add(deck.remove(0));
            }

            OuterCardStack outerCardStack = new OuterCardStack();
            outerCardStack.setPosition(outsideSextetLocations[i]);
            outerCardStack.setInternalStack(internalStack);
            outsideSextets.add(outerCardStack);
        }

        //Inner Piles
        for (int i = 0; i < 8; i++){
            InnerCardStack innerCardStack = new InnerCardStack();
            innerCardStack.setPosition(innerStackLocations[i]);

            innerCardStack.stackFace.setLocation(innerCardStack.getPosition());
            innerCardStack.stackFace.setSize(card_size[0], card_size[1]);
            innerCardStack.arrayPosition = i;
            innerCardStack.stackFace.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    hoveredInnerCardStack = innerCardStack.arrayPosition;
                    System.out.println(innerCardStack.arrayPosition);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    hoveredInnerCardStack = -1;
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("dfasfsdafsaf");
                }
            });
            innerStacks.add(innerCardStack);
        }

        innerStacks.get(0).getInternalStack().add(new Card(1, "C", "top"));
        innerStacks.get(1).getInternalStack().add(new Card(1, "S", "top"));
        innerStacks.get(2).getInternalStack().add(new Card(1, "H", "top"));
        innerStacks.get(3).getInternalStack().add(new Card(1, "D", "top"));
        innerStacks.get(4).getInternalStack().add(new Card(13, "C", "top"));
        innerStacks.get(5).getInternalStack().add(new Card(13, "S", "top"));
        innerStacks.get(6).getInternalStack().add(new Card(13, "H", "top"));
        innerStacks.get(7).getInternalStack().add(new Card(13, "D", "top"));

        for(int i = 0; i < 8; i++){
            InnerCardStack innerCardStack = innerStacks.get(i);
            innerCardStack.draw();
            CrescentSolitaire.add(innerCardStack.stackFace);
        }

        /* START OF MAKING THE GUI*/
        //1. Create the frame.
        JFrame frame = new JFrame("Crescent Solitaire");

        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
        //...create emptyLabel...


        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/logo.png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        JLabel dragCard = new JLabel();
        dragCard.setSize(card_size[0], card_size[1]);
        dragCard.setIcon(imageIcon);
        dragCard.validate();
        dragCard.setVisible(false);
        CrescentSolitaire.add(dragCard);
        frame.add(CrescentSolitaire);

        for (int i = 0; i < 16; i++) {
            OuterCardStack thisStack = outsideSextets.get(i);
            thisStack.stackFace.setLocation(thisStack.getPosition());
            thisStack.stackFace.setSize(card_size[0], card_size[1]);
            thisStack.stackFace.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    super.mousePressed(e);
                    final Point mousePos = CrescentSolitaire.getMousePosition();
                    super.mouseDragged(e);
                    int x = mousePos.x;
                    int y = mousePos.y;
                    dragCard.setLocation(x - (card_size[0] / 2), y - (card_size[1] / 2));
                    dragCard.setIcon(thisStack.dragCardFace());
                    dragCard.setVisible(true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    if(hoveredInnerCardStack != -1){
                        InnerCardStack innerCardStack = innerStacks.get(hoveredInnerCardStack);
                        if(canPlace(draggedCard, innerCardStack.getInternalStack().get(0), score)[0] == 1){
                            innerCardStack.successfulDrag(draggedCard);
                            thisStack.successfulDrag();
                        }
                        else{
                            thisStack.failedDrag();
                        }

                    }
                    else{
                        thisStack.failedDrag();
                    }

                    thisStack.dragging = false;
                    dragCard.setVisible(false);
                }
            });

            thisStack.stackFace.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    final Point mousePos = CrescentSolitaire.getMousePosition();
                    super.mouseDragged(e);
                    int x = mousePos.x;
                    int y = mousePos.y;
                    dragCard.setLocation(x - (card_size[0] / 2), y - (card_size[1] / 2));
                }
            });
            CrescentSolitaire.add(thisStack.stackFace);
        }

        frame.add(CrescentSolitaire);

        //4. Size the frame.
        frame.setSize(windowSize[0], windowSize[1]);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        //5. Show it.
        frame.setVisible(true);


    } //main()


    static int[] canPlace(Card source, Card destination, int score) {
        if (source.getType().equals(destination.getType()) && source.getValue() == destination.getValue() + 1 && source.getSuit().equals(destination.getSuit())) {
            return new int[]{
                    1,
                    score + 5
            };
        } else if (source.getType().equals(destination.getType()) && source.getValue() == destination.getValue() - 1 && source.getSuit().equals(destination.getSuit())) {
            return new int[]{
                    1,
                    score + 5
            };
        } else if (destination.getType().equals("top") && source.getValue() == destination.getValue() + 1 && source.getSuit().equals(destination.getSuit())) {
            return new int[]{
                    1,
                    score + 5
            };
        } else if (destination.getType().equals("bottom") && source.getValue() == destination.getValue() - 1 && source.getSuit().equals(destination.getSuit())) {
            return new int[]{
                    1,
                    score + 5
            };
        } else {
            return new int[]{
                    0,
                    score
            };
        }

    } //canPlace()
}
