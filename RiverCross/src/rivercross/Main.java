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

public class Main extends Application {
    
    Level1 level1= new Level1();
    List<Water> waterpic = new ArrayList<>();
    BackGround bg = new BackGround();

    @Override
    public void start(Stage theStage) throws InterruptedException {
        
        theStage.setTitle("Level 1");
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 178, 480);
            waterpic.add(w);
        }
        
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        
        Canvas canvas = new Canvas(995, 560);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        
        new AnimationTimer() {

            @Override
            public void handle(long l) {
                level1.draw(gc,bg,waterpic);
                level1.moves(theScene);
            }

        }.start();
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
