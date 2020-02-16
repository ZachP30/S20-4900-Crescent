import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.*;

public class OuterCardStack {

    private ArrayList<Card> internalStack;
    private Point position;
    public ImageIcon faceImage;
    public JLabel stackFace;
    public boolean dragging;
    public int arrayPosition;


    public OuterCardStack() {
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

    public void setPosition(Point position) {
        this.position = position;
    }//setPosition

    public ArrayList<Card> getInternalStack() {
        return this.internalStack;
    } //setValue

    public Point getPosition() {
        return this.position;
    }//setPosition

    public ImageIcon dragCardFace() {
        if (this.internalStack.size() > 1) {
            faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(1).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
            this.stackFace.setIcon(faceImage);
            this.stackFace.validate();
        } else {
            this.stackFace.setIcon(new ImageIcon());
            this.stackFace.validate();
        }


        return new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
    }

    public void successfulDrag() {
        if (this.internalStack.size() > 0) {
            this.internalStack.remove(0);

        }

        if (this.internalStack.size() == 0) {
            CardTester.finishedStacks += 1;
        } else {
            faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
            this.stackFace.setIcon(faceImage);
            this.stackFace.validate();
        }
    }

    public void failedDrag() {
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        this.stackFace.setIcon(faceImage);
        this.stackFace.validate();
    }

    public void deal() {
        if (this.internalStack.size() > 1) {
            this.internalStack.add(0, this.internalStack.remove(this.internalStack.size() - 1));
            faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
            this.stackFace.setIcon(faceImage);
            this.stackFace.validate();
        }
    }

    public ImageIcon successfulDrag(Card draggedCard) {
        this.internalStack.add(0, draggedCard);
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        this.stackFace.setIcon(faceImage);
        this.stackFace.validate();

        return new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
    }
} //End Card Class
