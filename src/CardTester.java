import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardTester {

    public static void main(String[] args) {
        int[] card_size = new int[]{100, 136};
        int[] windowSize = new int[]{1200, 800};
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

//   Collections.shuffle(deck);

        //Piles of outside cards
        ArrayList<CardStack> outsideSextets = new ArrayList<CardStack>();

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


        Point[] insideStackLocations = new Point[]{
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

            CardStack cardStack = new CardStack();
            cardStack.setPosition(outsideSextetLocations[i]);
            cardStack.setInternalStack(internalStack);
            outsideSextets.add(cardStack);
        }

        //Inner Piles
        Card[] topLeft = new Card[13];
        Card[] topSecond = new Card[13];
        Card[] topThird = new Card[13];
        Card[] topRight = new Card[13];
        Card[] bottomLeft = new Card[13];
        Card[] bottomSecond = new Card[13];
        Card[] bottomThird = new Card[13];
        Card[] bottomRight = new Card[13];

        //Builds the inside piles of cards to be stacked on top of
        Card testCard = new Card(1, "Clubs", "top");
        topLeft[0] = testCard;
        Card testCard1 = new Card(1, "Spades", "top");
        topSecond[0] = testCard1;
        Card testCard2 = new Card(1, "Hearts", "top");
        topThird[0] = testCard2;
        Card testCard3 = new Card(1, "Diamonds", "top");
        topRight[0] = testCard3;
        Card testCard4 = new Card(13, "Clubs", "bottom");
        bottomLeft[0] = testCard4;
        Card testCard5 = new Card(13, "Spades", "bottom");
        bottomSecond[0] = testCard5;
        Card testCard6 = new Card(13, "Hearts", "bottom");
        bottomThird[0] = testCard6;
        Card testCard7 = new Card(13, "Diamonds", "bottom");
        bottomRight[0] = testCard7;

//  for (int i = 0; i < 16; i++) {
//   CardStack thisStack = outsideSextets.get(i);
//   ArrayList < Card > intStack = thisStack.getInternalStack();
//   System.out.println(Arrays.toString(intStack.toArray()));  Prints out the Cards
//  }

        // Game logic
        // System.out.println(Arrays.toString(canPlace(outsideSextets.get(0).getInternalStack().get(1), topLeft[0], score)));
        // score = canPlace(outsideSextets.get(0).getInternalStack().get(1), topLeft[0], score)[1];
        // System.out.println(Arrays.toString(canPlace(outsideSextets.get(0).getInternalStack().get(2), topLeft[0], score)));
        // score = canPlace(outsideSextets.get(0).getInternalStack().get(1), topLeft[0], score)[1];
        // System.out.println(Arrays.toString(canPlace(outsideSextets.get(0).getInternalStack().get(1), topLeft[0], score)));
        // score = canPlace(outsideSextets.get(0).getInternalStack().get(1), topLeft[0], score)[1];
        // System.out.println(Arrays.toString(canPlace(outsideSextets.get(0).getInternalStack().get(2), topLeft[0], score)));
        // score = canPlace(outsideSextets.get(0).getInternalStack().get(1), topLeft[0], score)[1];

        /* START OF MAKING THE GUI*/
        //1. Create the frame.
        JFrame frame = new JFrame("Crescent Solitaire");

        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
        //...create emptyLabel...

        JPanel CrescentSolitaire = new JPanel();
        CrescentSolitaire.setLayout(null);
//  CrescentSolitaire.setLayout(null);
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/logo.png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        JLabel dragCard = new JLabel();
        dragCard.setSize(card_size[0], card_size[1]);
        dragCard.setIcon(imageIcon);
        dragCard.validate();
        dragCard.setVisible(false);
        CrescentSolitaire.add(dragCard);
        frame.add(CrescentSolitaire);

        for (int i = 0; i < 16; i++) {
            CardStack thisStack = outsideSextets.get(i);
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
                    thisStack.failedDrag();
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
