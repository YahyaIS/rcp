/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Raft {
    List<ICrosser> passengerList= new ArrayList<>();
    private int place;
    private Rectangle2D rec;
    private Rectangle2D moveRec;
    private int passengers=0;
    private int posX;
    private int posY;
    BufferedImage bim =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
        
    BufferedImage bi=new BufferedImage(500,300, BufferedImage.TYPE_INT_RGB);


    public Raft(){
        posX=180;
        posY=400;
       
        File input=new File("move.png");
        try {
            bim= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = SwingFXUtils.toFXImage(bi, null);
        File input_file = new File("raft.png");
        try {
            bi = ImageIO.read(input_file);
        } catch (IOException ex) {
            Logger.getLogger(Raft.class.getName()).log(Level.SEVERE, null, ex);
        }
            this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
            this.moveRec = new Rectangle2D(448,50,bim.getWidth(),bim.getHeight());
    }
    
    
    
    public void move(MouseEvent e)
    {
        
            
            if (place == 0&&passengers != 0) {
                posX += 460;
                
                //posY -= 15;
                setRec(rec);
                place++;
                passengers=0;
            } else if (place == 1&&passengers != 0) {
                posX -= 460;
                //posY += 15;
                setRec(rec);
                place--;
                passengers=0;
            }

        
    }
    
    public javafx.scene.image.Image getImage(){
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return  image;
    }
    public javafx.scene.image.Image getMoveImage(){
        Image image = SwingFXUtils.toFXImage(this.bim, null);
        return  image;
    }
    
    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }
    
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Rectangle2D getRec() {
        return rec;
    }

    public void setRec(Rectangle2D rec) {
        this.rec = rec;
    }

    public Rectangle2D getMoveRec() {
        return moveRec;
    }

    public void setMoveRec(Rectangle2D moveRec) {
        this.moveRec = moveRec;
    }
    
}