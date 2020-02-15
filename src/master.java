import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class Card {

    private int value;
    private String suit;
    private String type;

    public Card() {
        value = 0;
        suit = null;
        type = null;
    } //default Constructor

    public Card(int value, String suit, String type) {
        this.value = value;
        this.suit = suit;
        this.type = type;
    } //Constructor

    public void setValue(int value) {
        this.value = value;
    } //setValue

    public void setSuit(String suit) {
        this.suit = suit;
    } //setSuit

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }
    public String getSuit() {
        return suit;
    }

    public String getType() {
        return type;
    }


} //End Card Class

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class CardStack {

    private ArrayList<Card> internalStack;
    private int[] position;

    public CardStack() {
        position = new int[2];
        internalStack = new ArrayList<Card>(6);
    } //default Constructor

    public Card(int value, String suit, String type) {
        this.value = value;
        this.suit = suit;
        this.type = type;
    } //Constructor

    public void setValue(int value) {
        this.value = value;
    } //setValue

    public void setSuit(String suit) {
        this.suit = suit;
    } //setSuit

    public void setType(String type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }
    public String getSuit() {
        return suit;
    }

    public String getType() {
        return type;
    }


} //End Card Class

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class CardTester {
	public static void main(String[] args) {
			String[] suitList = new String[]{"Clubs","Spades", "Hearts", "Diamonds"};
			int[] valueList = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13};

			ArrayList<String> scoreTracker = new ArrayList<String>();

			String score = "142";
			String name = "Dax";

			scoreTracker.add(score + "," + name);
			System.out.println(scoreTracker.get(0));


			//Decks
			ArrayList<Card> deck = new ArrayList<Card>(96);

			//Inner Piles
			Card[] topLeft = new Card[13];
			Card[] topSecond = new Card[13];
			Card[] topThird = new Card[13];
			Card[] topRight = new Card[13];
			Card[] bottomLeft = new Card[13];
			Card[] bottomSecond = new Card[13];
			Card[] bottomThird = new Card[13];
			Card[] bottomRight = new Card[13];


			//Piles of outside cards
			ArrayList<ArrayList<Card>> outsideSextets = new  ArrayList<ArrayList<Card>>();
			for(int i = 0; i < 16; i++){
				outsideSextets.add(new ArrayList<Card>(6));

			}

			int k=0;
			for(int i=0; i<suitList.length; i++){
			  for(int j=1; j<valueList.length;j++){ //leaves out kings
				Card myCard = new Card(); // create new card with defaults
			    myCard.setValue(j);
			    myCard.setSuit(suitList[i]);
			    myCard.setType("sextet");
			    deck1[k] = myCard; // add card to deck
			    k++;
			  }
			}

			int z=0;
			for(int i=0; i<suitList.length; i++){
			  for(int j=2; j<=valueList.length;j++){ //leaves out aces
				Card myCard = new Card(); // create new card with defaults
			    myCard.setValue(j);
			    myCard.setSuit(suitList[i]);
			    myCard.setType("sextet");
			    deck2[z] = myCard;
			    z++;
			  }
			}




			//Builds the inside piles of cards to be stacked on top of
			Card testCard = new Card(1,"Clubs", "top");
			topLeft[0] = testCard;
			Card testCard1 = new Card(1,"Spades", "top");
			topSecond[0] = testCard1;
			Card testCard2 = new Card(1,"Hearts", "top");
			topThird[0] = testCard2;
			Card testCard3 = new Card(1,"Diamonds", "top");
			topRight[0] = testCard3;
			Card testCard4 = new Card(13,"Clubs", "bottom");
			bottomLeft[0] = testCard4;
			Card testCard5 = new Card(13,"Spades", "bottom");
			bottomSecond[0] = testCard5;
			Card testCard6 = new Card(13,"Hearts", "bottom");
			bottomThird[0] = testCard6;
			Card testCard7 = new Card(13,"Diamonds", "bottom");
			bottomRight[0] = testCard7;

			//Testing the assingment of cards
			/*System.out.println(topLeft[0].getValue() + " " + topLeft[0].getSuit());
			System.out.println(topRight[0].getValue() + " " + topRight[0].getSuit());
			System.out.println(bottomLeft[0].getValue() + " " + bottomLeft[0].getSuit());
			System.out.println(bottomRight[0].getValue() + " " + bottomRight[0].getSuit());*/

			shuffleArray(deck1);
			shuffleArray(deck2);

			for(int i=0; i<6; i++) {
				sextet1.add(deck1[i]);
			}
			for(int i=6; i<12; i++) {
				sextet2.add(deck1[i]);
			}
			for(int i=12; i<18; i++) {
				sextet3.add(deck1[i]);
			}
			for(int i=18; i<24; i++) {
				sextet4.add(deck1[i]);
			}
			for(int i=24; i<30; i++) {
				sextet5.add(deck1[i]);
			}
			for(int i=30; i<36; i++) {
				sextet6.add(deck1[i]);
			}
			for(int i=36; i<42; i++) {
				sextet7.add(deck1[i]);
			}
			for(int i=42; i<48; i++) {
				sextet8.add(deck1[i]);
			}
			for(int i=0; i<6; i++) {
				sextet9.add(deck2[i]);
			}
			for(int i=6; i<12; i++) {
				sextet10.add(deck2[i]);
			}
			for(int i=12; i<18; i++) {
				sextet11.add(deck2[i]);
			}
			for(int i=18; i<24; i++) {
				sextet12.add(deck2[i]);
			}
			for(int i=24; i<30; i++) {
				sextet13.add(deck2[i]);
			}
			for(int i=30; i<36; i++) {
				sextet14.add(deck2[i]);
			}
			for(int i=36; i<42; i++) {
				sextet15.add(deck2[i]);
			}
			for(int i=42; i<48; i++) {
				sextet16.add(deck2[i]);
			}

//			System.out.println(sextet1.get(1).getValue() + sextet1.get(1).getSuit() + sextet1.get(1).getType());
//			System.out.println(topLeft[0].getValue() + topLeft[0].getSuit() + topLeft[0].getType());


//			for(int i=0; i<deck1.length; i++){
//			  System.out.println(deck1[i].getValue() +  " " + deck1[i].getSuit());
//			}

			//To test the assignment and shuffle printing deck

//			System.out.println(canPlace(sextet1.get(1), topLeft[0]));
	}//main()




	static void shuffleArray(Card[] ar)
	{
	  Random rnd = ThreadLocalRandom.current();
	  for (int i = ar.length - 1; i > 0; i--)
	  {
	    int index = rnd.nextInt(i + 1);
	    Card a = ar[index];
	    ar[index] = ar[i];
	    ar[i] = a;
	  }
	}//shuffleArray()



	static boolean canPlace(Card source, Card destination) {
		if (source.getType().equals(destination.getType()) && source.getValue() == destination.getValue()+1 && source.getSuit().equals(destination.getSuit())){ //case where it is sextet to sextet
			return true;
		}
		else if (source.getType().equals(destination.getType()) && source.getValue() == destination.getValue()-1 && source.getSuit().equals(destination.getSuit())) {
			return true;
		}
		else if (destination.getType().equals("top") && source.getValue() == destination.getValue()+1 && source.getSuit().equals(destination.getSuit())){
			return true;
		}

		else if (destination.getType().equals("bottom") && source.getValue() == destination.getValue()-1 && source.getSuit().equals(destination.getSuit())) {
			return true;
		}

		else {
			return false;
		}


	/*	static void reshufflePiles(ArrayList<Card> sextet) {
			sextet.shuffleArray();
		} */
	}//canPlace()

}
