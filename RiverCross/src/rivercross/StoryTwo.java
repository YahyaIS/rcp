/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author seif
 */
public class StoryTwo {
    
    List<Water> waterpic = new ArrayList<>();
    Raft raft = new Raft();
    Farmer1 farmer1 = new Farmer1();
    Farmer2 farmer2 = new Farmer2();
    Farmer3 farmer3 = new Farmer3();
    Farmer4 farmer4 = new Farmer4();
    Cat cat = new Cat();
    Human human = new Human();
    Stage stage;   
    Group root = new Group();
    
    
    
    
    
    public void draw(Scene scene,GraphicsContext gc,BackGround bg){
      for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }
        
        
        Canvas canvas = new Canvas(995, 560);
        root.getChildren().add(canvas);
         
        Side left = new Side(1);
        Side right = new Side(2);
        
        
         new AnimationTimer() {

            @Override
            public void handle(long l) {
                
                bg.drawBackground(gc, waterpic);
                gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
                gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
                gc.drawImage(raft.getImage(), raft.getPosX(), raft.getPosY());
                gc.drawImage(farmer1.getImage(), farmer1.getPosX(), farmer1.getPosY());
                gc.drawImage(farmer2.getImage(), farmer2.getPosX(), farmer2.getPosY());
                gc.drawImage(farmer3.getImage(), farmer3.getPosX(), farmer3.getPosY());
                gc.drawImage(farmer4.getImage(), farmer4.getPosX(), farmer4.getPosY());
                gc.drawImage(cat.getImage(), cat.getPosX(), cat.getPosY());
                gc.drawImage(raft.getMoveImage(), 448, 50);
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) 
                            {
                                
                                raft.move1(e);
                                
                            }
                            else if (farmer1.getRec().contains(e.getX(), e.getY())) 
                            {
                                farmer1.move(e,raft);
                            }
                            else if (farmer2.getRec().contains(e.getX(), e.getY())) 
                            {
                                farmer2.move(e,raft);
                            }
                            else if (farmer3.getRec().contains(e.getX(), e.getY())) 
                            {
                                farmer3.move(e,raft);
                            }
                            else if (human.getRec().contains(e.getX(), e.getY())) 
                            {
                                farmer4.move(e,raft);
                                
                            }
                             else if (cat.getRec().contains(e.getX(), e.getY())) 
                            {
                                cat.move(e,raft);
                            }
                            
                        });
            }

        }.start();
        
        
        
        
        
        
    }

    

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    
    
    
    
}
