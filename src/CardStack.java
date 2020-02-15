import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

public class CardStack {

    private ArrayList<Card> internalStack;
    private Point position;
    public ImageIcon faceImage;
    public JLabel stackFace;
    public boolean dragging;


    public CardStack() {
        position = new Point(0, 0);
        internalStack = new ArrayList<Card>(6);
        stackFace = new JLabel();

    } //default Constructor

    public void setInternalStack(ArrayList<Card> internalStack) {
        this.internalStack = internalStack;
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        this.stackFace.setIcon(faceImage);
        this.stackFace.validate();
    } //setValue

    public void setPosition(Point position){
      this.position = position;
    }//setPosition

    public ArrayList<Card> getInternalStack() {
        return this.internalStack;
    } //setValue

    public Point getPosition(){
        return this.position;
    }//setPosition

    public ImageIcon dragCardFace(){
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(1).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        this.stackFace.setIcon(faceImage);
        this.stackFace.validate();

        return new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
    }

    public void failedDrag(){
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        this.stackFace.setIcon(faceImage);
        this.stackFace.validate();
    }
} //End Card Class
