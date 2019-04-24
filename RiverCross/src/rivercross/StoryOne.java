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

/**
 *
 * @author seif
 */
public class StoryOne implements ICrossingStrategy {

    List<Water> waterpic = new ArrayList<>();

    private Side left = new Side(1);
    private Side right = new Side(2);
    private Raft raft = new Raft();
    private Vegetables vegetables = new Vegetables();
    private Human farmer = new Human();
    private Wolf wolf = new Wolf();
    private Sheep sheep = new Sheep();
    private Group root = new Group();

    public void draw(Scene scene, GraphicsContext gc, BackGround bg) {
        getInitialCrossers();
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }

        Canvas canvas = new Canvas(995, 560);
        root.getChildren().add(canvas);

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
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) {
                                if (isValid(right.rightRaft, left.leftRaft, raft.passengerList)) {
                                    raft.move(e);
                                }
                                

                            } else if (farmer.getRec().contains(e.getX(), e.getY())) {
                                farmer.move(e, raft,left,right);
                            } else if (wolf.getRec().contains(e.getX(), e.getY())) {
                                wolf.move(e, raft,left,right);
                            } else if (sheep.getRec().contains(e.getX(), e.getY())) {
                                sheep.move(e, raft,left,right);
                            } else if (vegetables.getRec().contains(e.getX(), e.getY())) {
                                vegetables.move(e, raft,left,right);

                            }

                        });
            }

        }.start();

    }

    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        ICrosser crosser1 = boatRiders.remove(0);
        if ((leftBankCrossers.contains(wolf) && leftBankCrossers.contains(sheep))
                || (leftBankCrossers.contains(sheep) && leftBankCrossers.contains(vegetables))
                ||(rightBankCrossers.contains(wolf)&& rightBankCrossers.contains(sheep))
                ||(rightBankCrossers.contains(sheep)&&rightBankCrossers.contains(vegetables))) {
            boatRiders.add(crosser1);
            return false;
        } else {
            if (!boatRiders.isEmpty()) {
                ICrosser crosser2 = boatRiders.remove(0);
                boatRiders.add(crosser2);
                boatRiders.add(crosser1);
                return crosser1.canSail() || crosser2.canSail();
            }
            boatRiders.add(crosser1);
            return crosser1.canSail();
        }
    }

    @Override
    public List<ICrosser> getInitialCrossers() {
        List<ICrosser> crossers = new ArrayList<>();
        crossers.add(farmer);
        crossers.add(wolf);
        crossers.add(sheep);
        crossers.add(vegetables);
        left.leftRaft = crossers;
        return crossers;

    }

    @Override
    public String[] getInstructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
