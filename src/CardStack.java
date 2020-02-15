import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class CardStack {

    private ArrayList<Card> internalStack;
    private int[] position;

    public CardStack() {
        position = new int[2];
        internalStack = new ArrayList<Card>(6);
    } //default Constructor

    public void setInternalStack(ArrayList<Card> internalStack) {
        this.internalStack = internalStack;
    } //setValue

    public void setPosition(int[] position){
      this.position = position;
    }//setPosition

    public ArrayList<Card> getInternalStack() {
        return this.internalStack;
    } //setValue
} //End Card Class
