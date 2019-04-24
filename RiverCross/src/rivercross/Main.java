package rivercross;

import javafx.application.Application;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import static javafx.application.Application.launch;

public class Main extends Application {

    List<Water> waterpic = new ArrayList<>();
    BackGround bg = new BackGround();
    Menu menu=new Menu();
    
    @Override
    public void start(Stage theStage) throws InterruptedException {
        theStage.setTitle("River crosing puzzle");
        menu.draw(theStage);
    }

    public static void main(String[] args) {
        launch(args);

    }

}
