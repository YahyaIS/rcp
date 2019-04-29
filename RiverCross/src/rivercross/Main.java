package rivercross;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Main extends Application {

    List<Water> waterpic = new ArrayList<>();
    BackGround bg = new BackGround();
    Menu menu=new Menu();
  //  Xml x//ml=new Xml();
    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("River crosing puzzle");
        
        Canvas canvas = new Canvas(995, 560);
        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        menu.draw(theScene, gc);
        theStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
