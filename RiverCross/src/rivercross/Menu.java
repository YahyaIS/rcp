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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private Rectangle2D rec1;
    private Rectangle2D rec2;

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
    }

    public void draw(Scene theScene, GraphicsContext gc) {
        
//        for (int i = 0; i < 7; i++) {
//            Water w = new Water(i * 179, 480);
//            waterpic.add(w);
//        }
//
//        Side left = new Side(1);
//        Side right = new Side(2);
        
        storyOne.setMenu(this);
        storyTwo.setMenu(this);
        new AnimationTimer() {

            @Override
            public void handle(long l) {

                bg.drawBackground(gc, waterpic);
//                gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
//                gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
                gc.drawImage(image1, 300, 200);
                gc.drawImage(image2, 500, 200);
                theScene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (rec1.contains(e.getX(), e.getY())) {
                        storyOne.initPos();
                        storyOne.draw(theScene, gc, bg);
                        
                    } else if (rec2.contains(e.getX(), e.getY())) {
                        storyTwo.initPos();
                        storyTwo.draw(theScene, gc, bg);
                    }
                });
            }
        }.start();

    }
}
