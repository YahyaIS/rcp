
package rivercross;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class BackGround {
    //Water water;
    
    
    
    public BackGround(){
    }

    public void drawBackground(GraphicsContext gc, List<Water> waterpic) {
        Iterator<Water>itt=waterpic.iterator();
        while (itt.hasNext()){
            Water next=itt.next();
            next.setPosX(next.getPosX()+10);
            if(next.getPosX()>900){
                next.setPosX(0);
            }
            next.setPosY(next.getPosY());
        }
        
        BufferedImage bi =new BufferedImage(100,200,BufferedImage.TYPE_INT_RGB);
        File input_file = new File("asd.png");
        try {
            bi = ImageIO.read(input_file);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = SwingFXUtils.toFXImage(bi, null);
        gc.drawImage(image,0,0);

        itt=waterpic.iterator();
        while (itt.hasNext()){
            Water next=itt.next();
            gc.drawImage(next.getImage(),next.getPosX(),next.getPosY());
        }
    }
}
