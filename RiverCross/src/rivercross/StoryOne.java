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

/**
 *
 * @author seif
 */
public class StoryOne implements ICrossingStrategy {

    List<Water> waterpic = new ArrayList<>();

    private Side left = new Side(1);
    private Side right = new Side(2);

    private Raft raft = new Raft();
    private Human farmer = new Human();
    private Vegetables vegetables = new Vegetables();
    private Wolf wolf = new Wolf();
    private Sheep sheep = new Sheep();
    private boolean x=false;
    private Group root = new Group();
    Momento m=new Momento();
    Momento momento;
    Stack<Momento>undo=new Stack<>();
    Stack<Momento>redo=new Stack<>();

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
                gc.drawImage(m.getUndoImage(),100,50);
                gc.drawImage(m.getRedoImage(),800,50);
                gc.drawImage(raft.getMoveImage(), 448, 50);
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) {
                                if (isValid(right.rightRaft, left.leftRaft, raft.passengerList)) {
                                    if(x==false){
                                        momento=new Momento(left.getLeftRaft(),right.getRightRaft(),raft.getPassengerList(),momentodata());
                                        undo.add(momento);
                                        x=true;
                                    }
                                        raft.move(e);
                                    momento=new Momento(left.getLeftRaft(),right.getRightRaft(),raft.getPassengerList(),momentodata());
                                    undo.add(momento);
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
                            else if(m.getUndorec().contains(e.getX(),e.getY())&&!undo.isEmpty()&&undo.size()!=1){
                                    redo.push(undo.pop());
                                    setPositions(undo);

                            }
                            else if(m.getRedorec().contains(e.getX(),e.getY())&&!redo.isEmpty()) {
                                   // if(redo.size()!=1){
                                setPositions(redo);
                                undo.push(redo.pop());


                                //}
                            }


                        });
            }

        }.start();

    }
    public void setPositions(Stack<Momento>undo){
        raft.setPosX(undo.peek().getRaftX());
        raft.setPosY(undo.peek().getRaftY());
        farmer.setPosX(undo.peek().getChar1X());
        farmer.setPosY(undo.peek().getChar1Y());
        vegetables.setPosX(undo.peek().getChar2X());
        vegetables.setPosY(undo.peek().getChar2Y());
        wolf.setPosX(undo.peek().getChar3X());
        wolf.setPosY(undo.peek().getChar3Y());
        sheep.setPosX(undo.peek().getChar4X());
        sheep.setPosY(undo.peek().getChar4Y());
        left.setLeftRaft(undo.peek().getLeft());
        right.setRightRaft(undo.peek().getRight());
        raft.setPassengerList(undo.peek().getPassengerList());
        raft.setPlace(undo.peek().getRaftplace());
        farmer.setPlace(undo.peek().getPlace1());
        vegetables.setPlace(undo.peek().getPlace2());
        wolf.setPlace(undo.peek().getPlace3());
        sheep.setPlace(undo.peek().getPlace4());
    }

    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {

        if(!boatRiders.isEmpty()) {
        ICrosser crosser1 = boatRiders.remove(0);

            if ((leftBankCrossers.contains(wolf) && leftBankCrossers.contains(sheep))
                    || (leftBankCrossers.contains(sheep) && leftBankCrossers.contains(vegetables))
                    || (rightBankCrossers.contains(wolf) && rightBankCrossers.contains(sheep))
                    || (rightBankCrossers.contains(sheep) && rightBankCrossers.contains(vegetables))) {
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
        return false;
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


    public int[] momentodata(){
        int arr[]=new int[17];arr[0]=raft.getPosX();
        arr[1]=raft.getPosY();

        arr[2]=farmer.getPosX();
        arr[3]=farmer.getPosY();

        arr[4]=vegetables.getPosX();
        arr[5]=vegetables.getPosY();

        arr[6]=wolf.getPosX();
        arr[7]=wolf.getPosY();

        arr[8]=sheep.getPosX();
        arr[9]=sheep.getPosY();

        arr[10]=raft.getPlace();
        arr[11]=farmer.getPlace();
        arr[12]=vegetables.getPlace();
        arr[13]=wolf.getPlace();
        arr[14]=sheep.getPlace();
        arr[15]=0;
        return  arr;
    }



}

