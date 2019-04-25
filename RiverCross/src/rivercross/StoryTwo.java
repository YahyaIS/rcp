/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class StoryTwo implements ICrossingStrategy {


    private Side left = new Side(1);
    private Side right = new Side(2);
    private List<Water> waterpic = new ArrayList<>();
    private Raft raft = new Raft();
    private Farmer1 farmer1 = new Farmer1();
    private Farmer2 farmer2 = new Farmer2();
    private Farmer3 farmer3 = new Farmer3();
    private Farmer4 farmer4 = new Farmer4();
    private Cat cat = new Cat();
    private Human human = new Human();
    private Group root = new Group();
    Momento m = new Momento();
    Momento momento;
    private boolean x=false;
    Stack<Momento> undo = new Stack<>();
    Stack<Momento> redo = new Stack<>();

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
                gc.drawImage(farmer3.getImage(), farmer3.getPosX(), farmer3.getPosY());
                gc.drawImage(farmer4.getImage(), farmer4.getPosX(), farmer4.getPosY());
                gc.drawImage(farmer2.getImage(), farmer2.getPosX(), farmer2.getPosY());
                gc.drawImage(farmer1.getImage(), farmer1.getPosX(), farmer1.getPosY());
                gc.drawImage(cat.getImage(), cat.getPosX(), cat.getPosY());
                gc.drawImage(m.getUndoImage(), 100, 50);
                gc.drawImage(m.getRedoImage(), 800, 50);
                gc.drawImage(raft.getMoveImage(), 448, 50);
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) {
                                if (raft.getWeightsum() <= 100 && isValid(null, null, raft.passengerList)) {
                                    if(x==false){
                                        momento=new Momento(left.getLeftRaft(),right.getRightRaft(),raft.getPassengerList(),momentodata());
                                        undo.add(momento);
                                        x=true;
                                    }
                                    raft.move(e);
                                    momento=new Momento(left.getLeftRaft(),right.getRightRaft(),raft.getPassengerList(),momentodata());
                                    undo.add(momento);
                                }
                            } else if (farmer1.getRec().contains(e.getX(), e.getY())) {
                                farmer1.move(e, raft,left,right);
                            } else if (farmer2.getRec().contains(e.getX(), e.getY())) {
                                farmer2.move(e, raft);
                            } else if (farmer3.getRec().contains(e.getX(), e.getY())) {
                                farmer3.move(e, raft);
                            } else if (farmer4.getRec().contains(e.getX(), e.getY())) {
                                farmer4.move(e, raft);

                            } else if (cat.getRec().contains(e.getX(), e.getY())) {
                                cat.move(e, raft);
                            }
                            else if(m.getUndorec().contains(e.getX(),e.getY())&&!undo.isEmpty()&&undo.size()!=1){
                                redo.push(undo.pop());
                                setPositions(undo);

                            }
                            else if(m.getRedorec().contains(e.getX(),e.getY())&&!redo.isEmpty()) {
                                // if(redo.size()!=1){
                                setPositions(redo);
                                undo.push(redo.pop());
                            }


                            });
            }

        }.start();
    }

    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        ICrosser crosser1 = boatRiders.remove(0);
        if (!boatRiders.isEmpty()) {
            ICrosser crosser2 = boatRiders.remove(0);
            boatRiders.add(crosser2);
            boatRiders.add(crosser1);
            return true;
        }
        boatRiders.add(crosser1);
        return crosser1 != cat;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {
        List<ICrosser> crossers = new ArrayList<>();
        crossers.add(farmer1);
        crossers.add(farmer2);
        crossers.add(farmer3);
        crossers.add(farmer4);
        crossers.add(cat);
        left.leftRaft = crossers;
        return crossers;
    }

    @Override
    public String[] getInstructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int[] momentodata() {
        int arr[] = new int[19];
        arr[0] = raft.getPosX();
        arr[1] = raft.getPosY();

        arr[2] = farmer1.getPosX();
        arr[3] = farmer1.getPosY();

        arr[4] = farmer2.getPosX();
        arr[5] = farmer2.getPosY();

        arr[6] = farmer3.getPosX();
        arr[7] = farmer3.getPosY();

        arr[8] = farmer4.getPosX();
        arr[9] = farmer4.getPosY();


        arr[10] = raft.getPlace();
        arr[11] = farmer1.getPlace();
        arr[12] = farmer2.getPlace();
        arr[13] = farmer3.getPlace();
        arr[14] = farmer4.getPlace();

        arr[15] = 1;
        arr[16] = cat.getPosX();
        arr[17] = cat.getPosY();
        arr[18] = cat.getPlace();
        return arr;
    }


    public void setPositions(Stack<Momento> undo) {
        raft.setPosX(undo.peek().getRaftX());
        raft.setPosY(undo.peek().getRaftY());
        farmer1.setPosX(undo.peek().getChar1X());
        farmer1.setPosY(undo.peek().getChar1Y());
        farmer2.setPosX(undo.peek().getChar2X());
        farmer2.setPosY(undo.peek().getChar2Y());
        farmer3.setPosX(undo.peek().getChar3X());
        farmer3.setPosY(undo.peek().getChar3Y());
        farmer4.setPosX(undo.peek().getChar4X());
        farmer4.setPosY(undo.peek().getChar4Y());
        raft.setPassengerList(undo.peek().getPassengerList());
        raft.setPlace(undo.peek().getRaftplace());

        farmer1.setPlace(undo.peek().getPlace1());
        farmer2.setPlace(undo.peek().getPlace2());
        farmer3.setPlace(undo.peek().getPlace3());
        farmer4.setPlace(undo.peek().getPlace4());

        cat.setPosX(undo.peek().getCatX());
        cat.setPosY(undo.peek().getCatY());
        cat.setPlace(undo.peek().getCatPlace());
    }

}




