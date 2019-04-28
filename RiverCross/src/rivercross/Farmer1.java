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
    private  int weight;
    private int eatingRank = 0;
    private int x;
    private int deltaX,deltaY;
    private int id;

    public int getId() {
        return id;
    }

    BufferedImage bi = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);

    public Farmer1(int x) {
        this.x=x;
        if(x==1) {
            File input = new File("farmer1.png");
            try {
                bi = ImageIO.read(input);
            } catch (IOException ex) {
                Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
            }

            posX = 30;
            posY = 420;
            this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
            weight=40;
            deltaX=150;
            deltaY=15;
            id=1;
        }
        else if(x==4){
            File input = new File("farmer4.png");
            try {
                bi = ImageIO.read(input);
            } catch (IOException ex) {
                Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
            }

            posX = 87;
            posY = 365;
            this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
            weight=80;
            deltaX=150;
            deltaY=17;
            id=4;

        }
        else if(x==3){

            File input = new File("farmer3.png");
            try {
                bi = ImageIO.read(input);
            } catch (IOException ex) {
                Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
            }

            posX = 140;
            posY = 362;
            this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
            weight=90;
            deltaX=150;
            deltaY=15;
            id=3;
        }
        else if(x==2) {
            File input = new File("farmer2.png");
            try {
                bi = ImageIO.read(input);
            } catch (IOException ex) {
                Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
            }

            posX = 50;
            posY = 373;
            this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
            weight=60;
            deltaX=160;
            deltaY=5;
            id=2;
        }
    }

    public void move(MouseEvent e,Raft raft) {
        
            if (place == 0&&raft.getPassengers() < 2&&raft.getPlace()==0) {

                posX += deltaX;
                posY += deltaY;
                setRec(rec);
                place++;
                raft.addList(this);
                raft.setPassengers(raft.getPassengers()+1);
                raft.setWeightsum(raft.getWeightsum()+weight);
            } else if (place == 1) {
                posX -= deltaX;
                posY -= deltaY;
                setRec(rec);
                place--;
                raft.removeList(this);
                raft.setPassengers(raft.getPassengers()-1);
                raft.setWeightsum(raft.getWeightsum()-weight);
        }
            else if(place==2)
            {
                posX+=deltaX;
                posY-=deltaY;
                setRec(rec);
                place++;
                raft.removeList(this);
                raft.setPassengers(raft.getPassengers()-1);
                raft.setWeightsum(raft.getWeightsum()-weight);

            }
            else if(place==3&&raft.getPassengers() < 2&&raft.getPlace()==1)
            {
                posX-=deltaX;
                posY+=deltaY;
                setRec(rec);
                place--;
                raft.addList(this);
                raft.setPassengers(raft.getPassengers()+1);
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
