package rivercross;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Momento {

    List<ICrosser> passengerList;
    private int raftX;
    private int raftY;

    private int char1X;
    private int char1Y;

    public int getRaftX() {
        return raftX;
    }

    public int getRaftY() {
        return raftY;
    }

    public int getChar1X() {
        return char1X;
    }

    public int getChar1Y() {
        return char1Y;
    }

    public int getChar2X() {
        return char2X;
    }

    public int getChar2Y() {
        return char2Y;
    }

    public int getChar3X() {
        return char3X;
    }

    public int getChar3Y() {
        return char3Y;
    }

    public int getChar4X() {
        return char4X;
    }

    public int getChar4Y() {
        return char4Y;
    }

    private int char2X;
    private int char2Y;

    private int char3X;
    private int char3Y;

    private int char4X;
    private int char4Y;

    private int raftplace;
    private int place1;
    private int place2;
    private int place3;
    private int place4;


    public Rectangle2D getUndorec() {
        return undorec;
    }

    public Rectangle2D getRedorec() {
        return redorec;
    }

    private   BufferedImage undoImage = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    private   BufferedImage redoImage = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
    private   Rectangle2D undorec;
    private   Rectangle2D redorec;
    private  List<ICrosser> left;
    private  List<ICrosser> right;

    public Momento(){
        this.undorec = new Rectangle2D(100, 50, undoImage.getWidth(), undoImage.getHeight());
        this.redorec = new Rectangle2D(800, 50, redoImage.getWidth(), redoImage.getHeight());

    }

    public List<ICrosser> getLeft() {
        return left;
    }

    public List<ICrosser> getRight() {
        return right;
    }

    public List<ICrosser> getPassengerList() {
        return passengerList;
    }

    public int getRaftplace() {
        return raftplace;
    }

    public int getPlace1() {
        return place1;
    }

    public int getPlace2() {
        return place2;
    }

    public int getPlace3() {
        return place3;
    }

    public int getPlace4() {
        return place4;
    }
    private int catX;
    private int catY;
    private int catPlace;

    public int getCatX() {
        return catX;
    }

    public int getCatY() {
        return catY;
    }

    public int getCatPlace() {
        return catPlace;
    }

    public Momento(List<ICrosser> left, List<ICrosser> right, List<ICrosser> passengerList , int ... numbers){
    raftX=numbers[0];
    raftY=numbers[1];

    char1X=numbers[2];
    char1Y=numbers[3];

    char2X=numbers[4];
    char2Y=numbers[5];

    char3X=numbers[6];
    char3Y=numbers[7];

    char4X=numbers[8];
    char4Y=numbers[9];
    raftplace=numbers[10];

    place1=numbers[11];
    place2=numbers[12];
    place3=numbers[13];
    place4=numbers[14];
    if(numbers[15]==1){
        catX=numbers[16];
        catY=numbers[17];
        catPlace=numbers[18];
    }

    this.left=left;
    this.right=right;
    this.passengerList=passengerList;
    }

    public javafx.scene.image.Image getUndoImage() {
        File input = new File("Undo.png");
        try {
            undoImage = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Momento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SwingFXUtils.toFXImage(this.undoImage, null);

    }

    public javafx.scene.image.Image getRedoImage() {
        File input = new File("Redo.png");
        try {
            redoImage = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Momento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SwingFXUtils.toFXImage(this.redoImage, null);
    }




}
