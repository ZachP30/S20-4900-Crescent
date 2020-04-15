import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;

public class CardTester {
    public static int hoveredInnerCardStack = -1;
    public static Card draggedCard = new Card();
    public static int score = 0;
    public static int finishedStacks = 0;
    public static int deals = 0;


    public ImageIcon background = new ImageIcon(new ImageIcon(this.getClass().getResource("background.jpg")).getImage());

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        int[] card_size = new int[]{100, 136};
        int[] windowSize = new int[]{1200, 800};
        JLabel scoreLabel = new JLabel();
        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        scoreLabel.setForeground(Color.white);
        scoreLabel.setText("Score: 0");
        scoreLabel.setSize(2000, 14);
        scoreLabel.setLocation(0, 0);
        scoreLabel.validate();


        JPanel CrescentSolitaire = new JPanel();
        CrescentSolitaire.setLayout(null);
        CrescentSolitaire.add(scoreLabel);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/logo.png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        JLabel dragCard = new JLabel();
        dragCard.setSize(card_size[0], card_size[1]);
        dragCard.setIcon(imageIcon);
        dragCard.validate();
        dragCard.setVisible(false);
        CrescentSolitaire.add(dragCard);

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
        scoreTracker.add(score + "," + playerName);

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

        Collections.shuffle(deck);

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
                new Point((windowSize[0] / 8) * 3 - card_size[0], (windowSize[1] / 5) * 4 - card_size[1]),
                new Point((windowSize[0] / 8) * 4 - card_size[0], (windowSize[1] / 5) * 4 - card_size[1]),
                new Point((windowSize[0] / 8) * 5 - card_size[0], (windowSize[1] / 5) * 4 - card_size[1]),
                new Point((windowSize[0] / 8) * 6 - card_size[0], (windowSize[1] / 5) * 4 - card_size[1]),
                new Point((windowSize[0] / 8) * 3 - card_size[0], (windowSize[1] / 5) * 5 - card_size[1]),
                new Point((windowSize[0] / 8) * 4 - card_size[0], (windowSize[1] / 5) * 5 - card_size[1]),
                new Point((windowSize[0] / 8) * 5 - card_size[0], (windowSize[1] / 5) * 5 - card_size[1]),
                new Point((windowSize[0] / 8) * 6 - card_size[0], (windowSize[1] / 5) * 5 - card_size[1]),
        };

        for (int i = 0; i < 16; i++) {
            ArrayList<Card> internalStack = new ArrayList<Card>();
            for (int j = 0; j < 6; j++) {
                internalStack.add(deck.remove(0));
            }

            OuterCardStack outerCardStack = new OuterCardStack();
            outerCardStack.arrayPosition = 8 + i;
            outerCardStack.setPosition(outsideSextetLocations[i]);
            outerCardStack.setInternalStack(internalStack);
            outsideSextets.add(outerCardStack);
        }

        //Inner Piles
        for (int i = 0; i < 8; i++) {
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
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    hoveredInnerCardStack = -1;
                }
            });
            innerStacks.add(innerCardStack);
        }

        innerStacks.get(0).getInternalStack().add(new Card(1, "C", "top"));
        innerStacks.get(0).setOrder(0);
        innerStacks.get(1).getInternalStack().add(new Card(1, "S", "top"));
        innerStacks.get(1).setOrder(0);
        innerStacks.get(2).getInternalStack().add(new Card(1, "H", "top"));
        innerStacks.get(2).setOrder(0);
        innerStacks.get(3).getInternalStack().add(new Card(1, "D", "top"));
        innerStacks.get(3).setOrder(0);
        innerStacks.get(4).getInternalStack().add(new Card(13, "C", "top"));
        innerStacks.get(4).setOrder(1);
        innerStacks.get(5).getInternalStack().add(new Card(13, "S", "top"));
        innerStacks.get(5).setOrder(1);
        innerStacks.get(6).getInternalStack().add(new Card(13, "H", "top"));
        innerStacks.get(6).setOrder(1);
        innerStacks.get(7).getInternalStack().add(new Card(13, "D", "top"));
        innerStacks.get(7).setOrder(1);

        for (int i = 0; i < 8; i++) {
            InnerCardStack innerCardStack = innerStacks.get(i);
            innerCardStack.draw();
            CrescentSolitaire.add(innerCardStack.stackFace);
        }

        /* START OF MAKING THE GUI*/
        //1. Create the frame.
        JFrame frame = new JFrame("Crescent Solitaire");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                double timeElapsed = (System.currentTimeMillis() - startTime) / 1000.;
                int minutes = (int) timeElapsed / 60;
                System.out.println("Time elapsed - " + minutes + "m " + (timeElapsed - minutes) + "s");
            }
        });

        //2. Optional: What happens when the frame closes?
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //3. Create components and put them in the frame.
        //...create emptyLabel...

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
                    draggedCard = thisStack.getInternalStack().get(0);
                    dragCard.setLocation(x - (card_size[0] / 2), y - (card_size[1] / 2));
                    dragCard.setIcon(thisStack.dragCardFace());
                    dragCard.setVisible(true);
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    super.mouseReleased(e);
                    if (0 <= hoveredInnerCardStack && hoveredInnerCardStack < 8) {
                        InnerCardStack innerCardStack = innerStacks.get(hoveredInnerCardStack);
                        if (canPlace(draggedCard, innerCardStack.getInternalStack().get(0), innerCardStack.getOrder())) {
                            innerCardStack.successfulDrag(draggedCard);
                            thisStack.successfulDrag();

                        } else {
                            thisStack.failedDrag();
                        }
                    } else if (hoveredInnerCardStack >= 8) {
                        OuterCardStack outerCardStack = outsideSextets.get(hoveredInnerCardStack - 8);
                        if (canPlace(draggedCard, outerCardStack.getInternalStack().get(0), 2)) {
                            outerCardStack.successfulDrag(draggedCard);
                            thisStack.successfulDrag();
                        } else {
                            thisStack.failedDrag();
                        }
                    } else {
                        thisStack.failedDrag();
                    }


                    if (finishedStacks == 16) {
                        scoreLabel.setText("You win!!!");
                        scoreLabel.setSize(1440, 900);
                        scoreLabel.setLocation(0, 0);
                        scoreLabel.setFont(new Font("Serif", Font.PLAIN, 150));
                        scoreLabel.validate();
                    }
                    thisStack.dragging = false;
                    dragCard.setVisible(false);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    hoveredInnerCardStack = thisStack.arrayPosition;
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    hoveredInnerCardStack = -1;
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

        JButton deal = new JButton();
        deal.setText("Deal");
        deal.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (int i = 0; i < 16; i++) {
                    OuterCardStack outerCardStack = outsideSextets.get(i);
                    outerCardStack.deal();
                }
                deals += 1;
                if(deals >= 3){
                    deal.setVisible(false);
                }
            }
        });


        deal.setLocation(1440 - 220, 0);
        deal.setSize(100, 50);
        CrescentSolitaire.add(deal);

        JButton newGame = new JButton();
        newGame.setText("New Game");
        newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
                final File currentJar;
                try {
                    currentJar = new File(CardTester.class.getProtectionDomain().getCodeSource().getLocation().toURI());
                    /* is it a jar file? */
                    if(!currentJar.getName().endsWith(".jar"))
                        return;

                    /* Build command: java -jar application.jar */
                    final ArrayList<String> command = new ArrayList<String>();
                    command.add(javaBin);
                    command.add("-jar");
                    command.add(currentJar.getPath());
                    final ProcessBuilder builder = new ProcessBuilder(command);
                    try {
                        builder.start();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });


        newGame.setLocation(1440 - 120, 0);
        newGame.setSize(100, 50);
        CrescentSolitaire.add(newGame);

        CardTester cardTester = new CardTester();
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setSize(1440, 900);
        backgroundLabel.setLocation(0, 0);
        backgroundLabel.setIcon(cardTester.background);
        backgroundLabel.validate();
        CrescentSolitaire.add(backgroundLabel);

        frame.add(CrescentSolitaire);

        //4. Size the frame.
        frame.setSize(1440, 900);
//        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        //5. Show it.
        frame.setVisible(true);

        while(true){
            double timeElapsed = (System.currentTimeMillis() - startTime) / 1000.;
            int minutes = (int) timeElapsed / 60;
            scoreLabel.setText("Time elapsed - " + minutes + "m " + (timeElapsed - minutes) + "s" + " | Score: " + score);
            Thread.sleep(100);
        }

    } //main()

    static boolean canPlace(Card source, Card destination, int order) {
        if (order == 0 && source.getValue() == destination.getValue() + 1 && source.getSuit().equals(destination.getSuit())) {
            CardTester.score += 5;
            return true;

        } else if (order == 1 && source.getValue() == destination.getValue() - 1 && source.getSuit().equals(destination.getSuit())) {
            CardTester.score += 5;
            return true;
        } else if (order == 2 && (source.getValue() == destination.getValue() - 1 || source.getValue() == destination.getValue() + 1) && source.getSuit().equals(destination.getSuit())) {
            CardTester.score += 5;
            return true;
        } else {
            return false;
        }

    } //canPlace()
}
