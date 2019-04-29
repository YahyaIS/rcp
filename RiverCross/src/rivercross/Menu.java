package rivercross;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;

public class Menu {
    StoryOne storyOne = new StoryOne();
    StoryTwo storyTwo = new StoryTwo();
    BackGround bg = new BackGround();
    List<Water> waterpic = new ArrayList<>();
    BufferedImage bi1 = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    Image image1;
    BufferedImage bi2 = new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    Image image2;

    BufferedImage Ng=new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    Image image3;

    BufferedImage lG=new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB);
    Image image4;

    private Rectangle2D rec1;
    private Rectangle2D rec2;
    private Rectangle2D rec3;
    private Rectangle2D rec4;

    public Menu() {

        File input = new File("story1label.png");
        try {
            bi1 = ImageIO.read(input);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rec1 = new Rectangle2D(300, 200, bi1.getWidth(), bi1.getHeight());
        image1 = SwingFXUtils.toFXImage(this.bi1, null);
        File input2 = new File("story2label.png");
        try {
            bi2 = ImageIO.read(input2);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rec2 = new Rectangle2D(500, 200, bi1.getWidth(), bi1.getHeight());
        image2 = SwingFXUtils.toFXImage(this.bi2, null);

        File input3 = new File("NewGame.png");
        try {
            Ng = ImageIO.read(input3);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rec3 = new Rectangle2D(350, 150, bi1.getWidth(), bi1.getHeight());
        image3 = SwingFXUtils.toFXImage(this.Ng, null);


        File input4 = new File("LoadGame.png");
        try {
            lG = ImageIO.read(input4);
        } catch (IOException ex) {
            Logger.getLogger(Side.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.rec4 = new Rectangle2D(350, 350, bi1.getWidth(), bi1.getHeight());
        image4 = SwingFXUtils.toFXImage(this.lG, null);




    }

    public void draw(Scene theScene, GraphicsContext gc) {
        
        storyOne.setMenu(this);
        storyTwo.setMenu(this);
        new AnimationTimer() {

            @Override
            public void handle(long l) {

                bg.drawBackground(gc, waterpic);
                gc.drawImage(image1, 300, 200);
                gc.drawImage(image2, 500, 200);
                theScene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (rec1.contains(e.getX(), e.getY())) {
                       choose(theScene,gc,1);
                    } else if (rec2.contains(e.getX(), e.getY())) {
                        choose(theScene,gc,2);
                    }
                });
            }
        }.start();

    }

    public void choose(Scene theScene,GraphicsContext gc,int level){
        new AnimationTimer() {
        @Override
        public void handle(long l) {
            bg.drawBackground(gc,waterpic);
            gc.drawImage(image3,350,150);
            gc.drawImage(image4,350,350);
            theScene.setOnMouseClicked(
                    (EventHandler<MouseEvent>) e -> {
                        if (rec3.contains(e.getX(), e.getY())) {
                            if(level==1) {
                                storyOne.initPos();
                                storyOne.draw(theScene, gc, bg);
                            }
                            else
                            {
                                storyTwo.initPos();
                                storyTwo.draw(theScene, gc, bg);
                            }
                        } else if (rec4.contains(e.getX(), e.getY())) {
                            if(level==1){
                                Xml x=new Xml("Level1.xml");
                                storyOne.initPos();
                                storyOne.setPositions(x.ReadXml());
                                storyOne.setAllRec();
                                storyOne.draw(theScene, gc, bg);
                            }
                            else
                            {
                                Xml x=new Xml("Level2.xml");
                                storyTwo.initPos();
                               storyTwo.setPositions( x.ReadXml());
                               storyTwo.setAllRec();
                               storyTwo.draw(theScene,gc,bg);
                            }
                        }
                    });
        }
        }.start();
    }
}
