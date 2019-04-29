/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.MouseEvent;

public class Human implements ICrosser {

    private int place = 0;
    private Rectangle2D rec;
    private int posX;
    private int posY;
    private final int weight = 80;
    private int eatingRank = 0;

    BufferedImage bi = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);

    public Human() {
        
        File input = new File("farmer4.png");
        try {
            bi = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Human.class.getName()).log(Level.SEVERE, null, ex);
        }

        posX = 120;
        posY = 365;
        this.rec = new Rectangle2D(posX, posY, bi.getWidth(), bi.getHeight());
    }

    public void move(MouseEvent e, Raft raft, Side left, Side right) {

        if (place == 0 && raft.getPassengers() < 2 && raft.getPlace() == 0) {
            posX += 150;
            posY -= 20;
            setRec(rec);
            place++;
            raft.addList(this);
            left.leftRaft.remove(new Integer (this.getId()));
            raft.setPassengers(raft.getPassengers() + 1);
        } else if (place == 1) {
            posX -= 150;
            posY += 20;
            setRec(rec);
            place--;
            raft.removeList(this);
            left.leftRaft.add(this.getId());
            raft.setPassengers(raft.getPassengers() - 1);

        } else if (place == 2) {
            posX += 150;
            posY += 15;
            setRec(rec);
            place++;
            raft.removeList(this);
            right.rightRaft.add(this.getId());
            raft.setPassengers(raft.getPassengers() - 1);
        } else if (place == 3 && raft.getPassengers() < 2 && raft.getPlace() == 1) {
            posX -= 150;
            posY -= 15;
            setRec(rec);
            place--;
            raft.addList(this);
            right.rightRaft.remove(new Integer(this.getId()));
            raft.setPassengers(raft.getPassengers() + 1);
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

    @Override
    public int getId() {
        return 1;
    }
    public void removeRec()
    {
        this.rec = new Rectangle2D(1000, 1000, bi.getWidth(), bi.getHeight());
    }
}