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
