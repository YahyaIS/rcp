/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

public class Water {
    

    private int PosX;
    private int PosY;
    BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
    public Water(int x,int y){
        File input=new File("image 1.png");
        try {
            bi= bi = ImageIO.read(input);
        }
        catch(IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        setPosX(x);
        setPosY(y);

    }
    
    
    public javafx.scene.image.Image getImage(){
        javafx.scene.image.Image image = SwingFXUtils.toFXImage(this.bi, null);
        return  image;
    }
    public int getPosX() {
        return PosX;
    }

    public void setPosX(int posX) {
        PosX = posX;
    }

    public int getPosY() {
        return PosY;
    }

    public void setPosY(int posY) {
        PosY = posY;
    }

}