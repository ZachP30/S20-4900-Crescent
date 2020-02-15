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

    public String toString(){
      String output = Integer.toString(this.value) + "" + this.suit;
      return output;
    }




} //End Card Class
