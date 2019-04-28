package rivercross;

import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@XmlRootElement
public class Momento {

    List<Integer> passengerList;
    private int raftX;
    private int raftY;

    private int char1X;
    private int char1Y;


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
    private int catX;
    private int catY;
    private int catPlace;


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
    private  List<Integer> left;
    private  List<Integer> right;

    public Momento(){
        this.undorec = new Rectangle2D(100, 50, undoImage.getWidth(), undoImage.getHeight());
        this.redorec = new Rectangle2D(800, 50, redoImage.getWidth(), redoImage.getHeight());

    }

    public List<Integer> getLeft() {
        return left;
    }

    public List<Integer> getRight() {
        return right;
    }

    public List<Integer> getPassengerList() {
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


    public int getCatX() {
        return catX;
    }

    public int getCatY() {
        return catY;
    }

    public int getCatPlace() {
        return catPlace;
    }

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

    public Momento(List<Integer> left, List<Integer> right, List<Integer> passengerList , int ... numbers){
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
    @XmlElement
    public void setPassengerList(List<Integer> passengerList) {
        this.passengerList = passengerList;
    }
    @XmlElement
    public void setRaftX(int raftX) {
        this.raftX = raftX;
    }
    @XmlElement
    public void setRaftY(int raftY) {
        this.raftY = raftY;
    }
    @XmlElement
    public void setChar1X(int char1X) {
        this.char1X = char1X;
    }
    @XmlElement
    public void setChar1Y(int char1Y) {
        this.char1Y = char1Y;
    }
    @XmlElement
    public void setChar2X(int char2X) {
        this.char2X = char2X;
    }
    @XmlElement
    public void setChar2Y(int char2Y) {
        this.char2Y = char2Y;
    }
    @XmlElement
    public void setChar3X(int char3X) {
        this.char3X = char3X;
    }
    @XmlElement
    public void setChar3Y(int char3Y) {
        this.char3Y = char3Y;
    }
    @XmlElement
    public void setChar4X(int char4X) {
        this.char4X = char4X;
    }
    @XmlElement
    public void setChar4Y(int char4Y) {
        this.char4Y = char4Y;
    }
    @XmlElement
    public void setRaftplace(int raftplace) {
        this.raftplace = raftplace;
    }
    @XmlElement
    public void setPlace1(int place1) {
        this.place1 = place1;
    }
    @XmlElement
    public void setPlace2(int place2) {
        this.place2 = place2;
    }
    @XmlElement
    public void setPlace3(int place3) {
        this.place3 = place3;
    }
    @XmlElement
    public void setPlace4(int place4) {
        this.place4 = place4;
    }
    @XmlElement
    public void setCatX(int catX) {
        this.catX = catX;
    }
    @XmlElement
    public void setCatY(int catY) {
        this.catY = catY;
    }
    @XmlElement
    public void setCatPlace(int catPlace) {
        this.catPlace = catPlace;
    }
    @XmlElement
    public void setUndoImage(BufferedImage undoImage) {
        this.undoImage = undoImage;
    }
    @XmlElement
    public void setRedoImage(BufferedImage redoImage) {
        this.redoImage = redoImage;
    }
    @XmlElement
    public void setUndorec(Rectangle2D undorec) {
        this.undorec = undorec;
    }
    @XmlElement
    public void setRedorec(Rectangle2D redorec) {
        this.redorec = redorec;
    }
    @XmlElement
    public void setLeft(List<Integer> left) {
        this.left = left;
    }
    @XmlElement
    public void setRight(List<Integer> right) {
        this.right = right;
    }
}
