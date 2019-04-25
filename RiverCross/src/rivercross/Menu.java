
package rivercross;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Menu {
    StoryOne storyOne = new StoryOne();
    StoryTwo storyTwo = new StoryTwo();
    BackGround bg = new BackGround();
    List<Water> waterpic = new ArrayList<>();
    public void draw(Stage theStage)
    {
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
            grid.relocate(1000,1000);
            storyOne.draw(theScene, gc, bg);
        });
        
        story2.setOnAction((ActionEvent event) -> {
            grid.relocate(1000,1000);
            storyTwo.draw(theScene, gc, bg);
        });
        
    new AnimationTimer() {

            @Override
            public void handle(long l) {
                
                bg.drawBackground(gc, waterpic);
                gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
                gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
            }
    }.start();
    theStage.show();
}
}