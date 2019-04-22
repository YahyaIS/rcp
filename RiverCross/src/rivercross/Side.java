/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import javax.imageio.ImageIO;

import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;

public class Side {
     List<ICrosser> leftBank= new ArrayList<>();
     List<ICrosser> rightBank= new ArrayList<>();
    private int xPos;
    private int YPos;

    

    BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public Side(int x){
        
        if(x==1){
            xPos=0;
            YPos=480;
            File input_file = new File("right.png");
            try {
                bi = ImageIO.read(input_file);
            } catch (IOException ex) {
                Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            xPos=820;
            YPos=480;
            File input_file = new File("left.png");
            try {
                bi = ImageIO.read(input_file);
            } catch (IOException ex) {
                Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }
    
    public void addLeftBank(ICrosser crosser)
    {
        leftBank.add(crosser);
    }
    
    public void addRightBank(ICrosser crosser)
    {
        rightBank.add(crosser);
    }
    
    public void removeLeftBank(ICrosser crosser)
    {
        leftBank.remove(crosser);
    }
    
    public void removeRightBank(ICrosser crosser)
    {
        rightBank.remove(crosser);
    }
    
    
    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return YPos;
    }

    public void setYPos(int YPos) {
        this.YPos = YPos;
    }

    public BufferedImage getBi() {
        return bi;
    }

    public Image getImage(){
        Image image = SwingFXUtils.toFXImage(this.bi, null);
      return  image;
    }

    public void setBi(BufferedImage bi) {
        this.bi = bi;
    }


}