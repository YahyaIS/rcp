/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

/**
 *
 * @author yahya
 */
public class Vegetables implements ICrosser{
    
    private int place=0;
    private Rectangle2D rec;
    private int posX;
    private int posY;
    private final int weight=0;
    private int eatingRank=3;
    BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);

    public Vegetables(){
        
        File input=new File("green.png");
        try {
            bi= ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        posX=2;
        posY=445;
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
    }
    
public void move(MouseEvent e,Raft raft) {
    
        if (rec.contains(e.getX(), e.getY())) {
            if (place == 0&&raft.getPassengers() < 2) {
                posX += 230;
          //      posY -= 25;
                setRec(rec);
                place++;
                raft.addList(this);
                raft.setPassengers(raft.getPassengers()+1);
                
            } else if (place == 1) {
                posX -= 230;
            //    posY += 15;
                setRec(rec);
                place--;
                raft.removeList(this);
                raft.setPassengers(raft.getPassengers()-1);
            }
            else if(place==2)
            {
                posX+=230;
          //      posY+=15;
                setRec(rec);
                place++;
                raft.removeList(this);
                raft.setPassengers(raft.getPassengers()-1);
            }
            else if(place==3)
            {
                posX-=230;
           //     posY-=15;
                setRec(rec);
                place--;
                raft.addList(this);
                raft.setPassengers(raft.getPassengers()+1);
            }
            

        }
    }
    
    @Override
    public boolean canSail() {
        return false;
    }

    @Override
    public double getWeight() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getEatingRank() {
        return 1;
    }

    @Override
    public BufferedImage getImages() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ICrosser makeCopy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLabelToBeShown(String label) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getLabelToBeShown() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        this.rec = new Rectangle2D(posX,posY,bi.getWidth(),bi.getHeight());
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

    public javafx.scene.image.Image getImage(){
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return  image;
    }
    
}