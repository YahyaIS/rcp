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
public class StoryOne {
    Side left = new Side(1);
    List<Water> waterpic = new ArrayList<>();
    Raft raft = new Raft();
    Vegetables vegetables= new Vegetables();
    Human farmer = new Human();
    Wolf wolf = new Wolf();
    Sheep sheep = new Sheep();
    BackGround bg = new BackGround();
    Group root = new Group();
    Scene scene = new Scene(root);
    Stage stage;
    
    
    
    public void draw(){
        
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }
        
        
        Canvas canvas = new Canvas(995, 560);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Side left = new Side(1);
        Side right = new Side(2);
        
        
         new AnimationTimer() {

            @Override
            public void handle(long l) {
                
                bg.drawBackground(gc, waterpic);
                gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
                gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
                gc.drawImage(raft.getImage(), raft.getPosX(), raft.getPosY());
                gc.drawImage(farmer.getImage(), farmer.getPosX(), farmer.getPosY());
                gc.drawImage(wolf.getImage(), wolf.getPosX(), wolf.getPosY());
                gc.drawImage(sheep.getImage(), sheep.getPosX(), sheep.getPosY());
                gc.drawImage(vegetables.getImage(), vegetables.getPosX(), vegetables.getPosY());
                gc.drawImage(raft.getMoveImage(), 448, 50);
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) 
                            {
                                raft.move(e);
                                
                            }
                            else if (farmer.getRec().contains(e.getX(), e.getY())) 
                            {
                                farmer.move(e,raft);
                            }
                            else if (wolf.getRec().contains(e.getX(), e.getY())) 
                            {
                                wolf.move(e,raft);
                            }
                            else if (sheep.getRec().contains(e.getX(), e.getY())) 
                            {
                                sheep.move(e,raft);
                            }
                            else if (vegetables.getRec().contains(e.getX(), e.getY())) 
                            {
                                vegetables.move(e,raft);
                                
                            }
                            
                        });
            }

        }.start();
        
        
        
        
        
        
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    
    
    
    
    
}
