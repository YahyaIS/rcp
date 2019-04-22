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
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Main extends Application {

    Side left = new Side(1);
    List<Water> waterpic = new ArrayList<>();
    Raft raft = new Raft();
    Human farmer = new Human();
    Wolf wolf = new Wolf();
    Sheep sheep = new Sheep();
    BackGround bg = new BackGround();

    @Override
    public void start(Stage theStage) throws InterruptedException {
        theStage.setTitle("Level 1");
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        
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
                gc.drawImage(raft.getMoveImage(), 448, 50);
                theScene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) 
                            {
                                raft.move(e);
                                
                            }
                            if (farmer.getRec().contains(e.getX(), e.getY())) 
                            {
                                farmer.move(e,raft);
                            }
                            if (wolf.getRec().contains(e.getX(), e.getY())) 
                            {
                                wolf.move(e,raft);
                            }
                            if (sheep.getRec().contains(e.getX(), e.getY())) 
                            {
                                sheep.move(e,raft);
                            }
                            
                        });
            }

        }.start();
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
