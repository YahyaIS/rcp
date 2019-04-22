package rivercross;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class Main extends Application {

    Side left = new Side(1);
    List<Water> waterpic = new ArrayList<>();
    Raft raft = new Raft();
    Vegetables vegetables= new Vegetables();
    Human farmer = new Human();
    Wolf wolf = new Wolf();
    Sheep sheep = new Sheep();
    BackGround bg = new BackGround();
    StoryOne storyOne = new StoryOne();
    StoryTwo storyTwo = new StoryTwo();
    
    

    @Override
    public void start(Stage theStage) throws InterruptedException {
        theStage.setTitle("River crosing puzzle");
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        GridPane grid = new GridPane();
        
        Canvas canvas = new Canvas(995, 560);

        root.getChildren().add(canvas);
        root.getChildren().add(grid);
        
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Side left = new Side(1);
        Side right = new Side(2);
        Button story1 = new Button("Story 1");
        Button story2 = new Button("Story 2");
        grid.add(story1, 40, 15);
        grid.add(story2, 40, 20);
        story1.setPrefSize(150, 50);
        story2.setPrefSize(150, 50);
        story1.setStyle("-fx-background-color: RED");
        story2.setStyle("-fx-background-color: RED");        
        story1.setTextFill(Color.YELLOW);
        story2.setTextFill(Color.YELLOW);
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        
        story1.setOnAction((ActionEvent event) -> {
            
            storyOne.setStage(theStage);
            storyOne.draw(theScene, gc, bg);
            
            
        });
        
        
        story2.setOnAction((ActionEvent event) -> {
            
            storyTwo.setStage(theStage);
            storyTwo.draw(theScene, gc, bg);
        });
        
        
        
        
        new AnimationTimer() {

            @Override
            public void handle(long l) {
                
                bg.drawBackground(gc, waterpic);
                gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
                gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
//                gc.drawImage(raft.getImage(), raft.getPosX(), raft.getPosY());
//                gc.drawImage(farmer.getImage(), farmer.getPosX(), farmer.getPosY());
//                gc.drawImage(wolf.getImage(), wolf.getPosX(), wolf.getPosY());
//                gc.drawImage(sheep.getImage(), sheep.getPosX(), sheep.getPosY());
//                gc.drawImage(vegetables.getImage(), vegetables.getPosX(), vegetables.getPosY());
//                gc.drawImage(raft.getMoveImage(), 448, 50);
//                theScene.setOnMouseClicked(
//                        (EventHandler<MouseEvent>) e -> {
//                            if (raft.getMoveRec().contains(e.getX(), e.getY())) 
//                            {
//                                raft.move(e);
//                                
//                            }
//                            else if (farmer.getRec().contains(e.getX(), e.getY())) 
//                            {
//                                farmer.move(e,raft);
//                            }
//                            else if (wolf.getRec().contains(e.getX(), e.getY())) 
//                            {
//                                wolf.move(e,raft);
//                            }
//                            else if (sheep.getRec().contains(e.getX(), e.getY())) 
//                            {
//                                sheep.move(e,raft);
//                            }
//                            else if (vegetables.getRec().contains(e.getX(), e.getY())) 
//                            {
//                                vegetables.move(e,raft);
//                                
//                            }
//                            
//                        });
            }

        }.start();
        
        
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
