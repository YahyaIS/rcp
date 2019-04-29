package rivercross;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class Options {

    private Rectangle2D backRec;
    private int backPosX;
    private int backPosY;
    private Rectangle2D restartRec;
    private Rectangle2D saveRec;
    private int restartPosX;
    private int savePosX;
    private int savePosY;
    private int restartPosY;
    BufferedImage bi = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
    BufferedImage bir = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
    BufferedImage bs = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);
    BufferedImage bw = new BufferedImage(500, 300, BufferedImage.TYPE_INT_RGB);

    public Rectangle2D getSaveRec() {
        return saveRec;
    }

    public int getSavePosX() {
        return savePosX;
    }

    public int getSavePosY() {
        return savePosY;
    }

    public Options() {
        backPosX = 50;
        backPosY = 150;
        restartPosX = 50;
        restartPosY = 200;
        savePosX = 890;
        savePosY = 70;
        File input_file = new File("backButton.png");
        try {
            bi = ImageIO.read(input_file);
        } catch (IOException ex) {
            Logger.getLogger(Raft.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.backRec = new Rectangle2D(backPosX, backPosY, bi.getWidth(), bi.getHeight());
        File restartFile = new File("restart.png");
        try {
            bir = ImageIO.read(restartFile);
        } catch (IOException ex) {
            Logger.getLogger(Raft.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.restartRec = new Rectangle2D(restartPosX, restartPosY, bir.getWidth(), bir.getHeight());

        File input_file2 = new File("Save.png");
        try {
            bs = ImageIO.read(input_file2);
        } catch (IOException ex) {
            Logger.getLogger(Raft.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.saveRec = new Rectangle2D(savePosX, savePosY, bs.getWidth(), bs.getHeight());
        File input_file3 = new File("win.jpg");
        try {
            bw = ImageIO.read(input_file3);
        } catch (IOException ex) {
            Logger.getLogger(Raft.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void removeRec() {
        this.backRec = new Rectangle2D(400, 250, bi.getWidth(), bi.getHeight());
        this.restartRec = new Rectangle2D(550, 250, bir.getWidth(), bir.getHeight());
    }

    public void setRec() {
        this.backRec = new Rectangle2D(backPosX,backPosY,bi.getWidth(),bi.getHeight());
        this.restartRec = new Rectangle2D(restartPosX,restartPosY,bir.getWidth(),bir.getHeight());
    }
    public javafx.scene.image.Image getSaveImage() {
        Image image = SwingFXUtils.toFXImage(this.bs, null);
        return image;

    }

    public javafx.scene.image.Image getImage() {
        Image image = SwingFXUtils.toFXImage(this.bi, null);
        return image;

    }

    public javafx.scene.image.Image getRestartImage() {
        Image image = SwingFXUtils.toFXImage(this.bir, null);
        return image;

    }

    public javafx.scene.image.Image getWinImage() {
        Image image = SwingFXUtils.toFXImage(this.bw, null);
        return image;

    }
    
    
    
    public int getBackPosX() {
        return backPosX;
    }

    public void setBackPosX(int posX) {
        this.backPosX = posX;
    }

    public int getBackPosY() {
        return backPosY;
    }

    public Rectangle2D getBackRec() {
        return backRec;
    }

    public Rectangle2D getRestartRec() {
        return restartRec;
    }

    public int getRestartPosX() {
        return restartPosX;
    }

    public int getRestartPosY() {
        return restartPosY;
    }

}
