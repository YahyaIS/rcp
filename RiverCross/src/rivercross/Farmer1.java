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
 * @author seif
 */
public class Farmer1 implements ICrosser {

    private int place = 0;
    private Rectangle2D rec;
    private int posX;
    private int posY;
    private final int weight = 40;
    private int eatingRank = 0;
    BufferedImage bi = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);

    public Farmer1() {

        File input = new File("farmer1.png");
        try {
            bi = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }

        posX = 30;
        posY = 435;
        this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
    }

    public void move(MouseEvent e,Raft raft) {
        
        
            
            if (place == 0&&raft.getWeightsum()<= 100) {
                posX += 150;
                posY -= 15;
                setRec(rec);
                place++;
                raft.addList(this);
                raft.setWeightsum(raft.getWeightsum()+weight);
            } else if (place == 1) {
                posX -= 150;
                posY += 15;
                setRec(rec);
                place--;
                raft.removeList(this);
                raft.setWeightsum(raft.getWeightsum()-weight);
                
        }
            else if(place==2)
            {
                posX+=150;
                posY+=15;
                setRec(rec);
                place++;
                raft.removeList(this);
                raft.setWeightsum(raft.getWeightsum()-weight);
            }
            else if(place==3)
            {
                posX-=150;
                posY-=15;
                setRec(rec);
                place--;
                raft.addList(this);
                raft.setWeightsum(raft.getWeightsum()+weight);
            }
    }

    public javafx.scene.image.Image getImage() {
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return image;
    }

    @Override
    public boolean canSail() {
        return true;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int getEatingRank() {
        return eatingRank;
    }

    @Override
    public BufferedImage getImages() {
        return null;
    }

    @Override
    public ICrosser makeCopy() {
        return null;
    }

    @Override
    public void setLabelToBeShown(String label) {

    }

    @Override
    public String getLabelToBeShown() {
        return null;
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

    public void setRec(Rectangle2D rec) {
        this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
    }

    public Rectangle2D getRec() {
        return rec;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        
        this.place = place;
    }

}
