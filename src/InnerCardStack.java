import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InnerCardStack {

    private ArrayList<Card> internalStack;
    private Point position;
    public ImageIcon faceImage;
    public JLabel stackFace;
    public int arrayPosition;


    public InnerCardStack() {
        position = new Point(0, 0);
        internalStack = new ArrayList<Card>();
        stackFace = new JLabel();

    } //default Constructor

    public void draw() {
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        System.out.println(this.internalStack.get(0).toString());
        System.out.println(position);
        System.out.println(stackFace.getSize());
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

    public ImageIcon successfulDrag(Card draggedCard){
        this.internalStack.add(0, draggedCard);
        faceImage = new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
        this.stackFace.setIcon(faceImage);
        this.stackFace.validate();

        return new ImageIcon(new ImageIcon("resources/faces/" + this.internalStack.get(0).toString() + ".png").getImage().getScaledInstance(100, 544 / 4, Image.SCALE_SMOOTH));
    }

} //End Card Class
