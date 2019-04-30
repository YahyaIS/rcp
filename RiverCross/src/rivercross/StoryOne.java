/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author seif
 */
public class StoryOne implements ICrossingStrategy {

    List<Water> waterpic = new ArrayList<>();
    private Side left = new Side(1);
    private Side right = new Side(2);
    private Options options = new Options();
    private Raft raft = new Raft();
    private Human farmer = new Human();
    private Vegetables vegetables = new Vegetables();
    private Wolf wolf = new Wolf();
    private Sheep sheep = new Sheep();
    private boolean x = false;
    private boolean f = true;
    private Momento m = new Momento();
    private Momento momento;
    private Stack<Momento> undo = new Stack<>();
    private Stack<Momento> redo = new Stack<>();
    private boolean h = true;
    private Menu menu;
    private int farmerX, farmerY, wolfX, wolfY, sheepX, sheepY, vegetablesX, vegetablesY, raftX, raftY;
    private Rectangle2D farmerRec, wolfRec, sheepRec, vegetablesRec, raftRec;

    public void draw(Scene scene, GraphicsContext gc, BackGround bg) {
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }

        new AnimationTimer() {

            @Override
            public void handle(long l) {
                 bg.drawBackground(gc, waterpic);
                if(f){
                gc.drawImage(options.getIImage(), 200, 100);
                gc.drawImage(options.getXImage(),750 , 125);
                gc.setTextAlign(TextAlignment.CENTER);
                    gc.setTextBaseline(VPos.CENTER);
                    gc.setFill(Color.RED);
                    gc.setFont(Font.font(16));
                    gc.fillText("1. The farmer is the only one who can sail the boat.\n" +
                    "He can only take one passenger, in addition to himself. " , 500, 170);
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.setTextBaseline(VPos.CENTER);
                    gc.setFill(Color.RED);
                    gc.setFont(Font.font(15));
                    gc.fillText("2. You can not leave any two crossers on the same bank \n" + "if they can harm(eat) each other" , 500, 250);
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.setTextBaseline(VPos.CENTER);
                    gc.setFill(Color.RED);
                    gc.setFont(Font.font(15));
                    gc.fillText("How can the farmer get across the river \n" + "with all the 2 animals and the plant without any losses?", 500, 350);
                }
                gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
                gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
                gc.drawImage(raft.getImage(), raft.getPosX(), raft.getPosY());
                gc.drawImage(farmer.getImage(), farmer.getPosX(), farmer.getPosY());
                gc.drawImage(wolf.getImage(), wolf.getPosX(), wolf.getPosY());
                gc.drawImage(sheep.getImage(), sheep.getPosX(), sheep.getPosY());
                gc.drawImage(vegetables.getImage(), vegetables.getPosX(), vegetables.getPosY());
                gc.drawImage(m.getUndoImage(), 100, 50);
                gc.drawImage(m.getRedoImage(), 800, 50);
                gc.drawImage(raft.getMoveImage(), 448, 50);
                gc.drawImage(options.getSaveImage(), options.getSavePosX(), options.getSavePosY());
                gc.drawImage(options.getImage(), options.getBackPosX(), options.getBackPosY());
                gc.drawImage(options.getRestartImage(), options.getRestartPosX(), options.getRestartPosY());
                gc.setTextAlign(TextAlignment.CENTER);
                gc.setTextBaseline(VPos.CENTER);
                gc.setFill(Color.BLACK);
                gc.setFont(Font.font(15));
                gc.fillText("Moves : " + raft.getMoves(), 70, 270);
                if (left.leftRaft.isEmpty() && raft.passengerList.isEmpty()) {
                    farmer.removeRec();
                    wolf.removeRec();
                    sheep.removeRec();
                    vegetables.removeRec();
                    m.removeRec();
                    options.removeRec();
                    gc.drawImage(options.getWinImage(), 300, 100);
                    gc.setTextAlign(TextAlignment.CENTER);
                    gc.setTextBaseline(VPos.CENTER);
                    gc.setFill(Color.ANTIQUEWHITE);
                    gc.setFont(Font.font(30));
                    gc.fillText("Level Complete ! " , 500, 150);
                    gc.drawImage(options.getImage(),400 , 250);
                    gc.drawImage(options.getRestartImage(), 550, 250);
                }
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (options.getXRec().contains(e.getX(), e.getY())) {
                                f=false;
                                
                            }
                        
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) {
                                if (isValid(getList(right.rightRaft), getList(left.leftRaft), raft.passengerList)) {
                                    if (x == false) {
                                        momento = new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata());
                                        undo.add(momento);
                                        x = true;
                                    }
                                    raft.move(e);
                                    momento = new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata());
                                    undo.add(momento);
                                }
                            } else if (farmer.getRec().contains(e.getX(), e.getY())) {
                                farmer.move(e, raft, left, right);
                            } else if (wolf.getRec().contains(e.getX(), e.getY())) {
                                wolf.move(e, raft, left, right);
                            } else if (sheep.getRec().contains(e.getX(), e.getY())) {
                                sheep.move(e, raft, left, right);
                            } else if (vegetables.getRec().contains(e.getX(), e.getY())) {
                                vegetables.move(e, raft, left, right);

                            } else if (m.getUndorec().contains(e.getX(), e.getY()) && !undo.isEmpty() && undo.size() != 1) {
                                if (raft.getMoves() > 0) {
                                    raft.setMoves(raft.getMoves() - 1);
                                }
                                redo.push(undo.pop());
                                setPositions(undo.peek());
                                setAllRec();
                            } else if (m.getRedorec().contains(e.getX(), e.getY()) && !redo.isEmpty()) {
                                raft.setMoves(raft.getMoves() + 1);
                                setPositions(redo.peek());
                                undo.push(redo.pop());
                                setAllRec();
                            } else if (options.getBackRec().contains(e.getX(), e.getY())) {
                                initPos();
                                menu.draw(scene, gc);
                            } else if (options.getRestartRec().contains(e.getX(), e.getY())) {
                                initPos();
                            } else if (options.getSaveRec().contains(e.getX(), e.getY())) {
                                Xml x = new Xml("Level1.xml");
                                try {
                                    x.createXml(new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata()));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                            }
                            
                        });
            }

        }.start();

    }

    public void initPos() {
        if (h == true) {
            farmerX = farmer.getPosX();
            farmerY = farmer.getPosY();
            farmerRec = farmer.getRec();
            wolfX = wolf.getPosX();
            wolfY = wolf.getPosY();
            wolfRec = wolf.getRec();
            sheepX = sheep.getPosX();
            sheepY = sheep.getPosY();
            sheepRec = sheep.getRec();
            vegetablesX = vegetables.getPosX();
            vegetablesY = vegetables.getPosY();
            vegetablesRec = vegetables.getRec();
            raftX = raft.getPosX();
            raftY = raft.getPosY();
            raftRec = raft.getRec();
            h = false;
            f= true;
        }
        m.setRec();
        options.setRec();
        raft.setMoves(0);
        farmer.setPosX(farmerX);
        farmer.setPosY(farmerY);
        farmer.setRec(farmerRec);
        farmer.setPlace(0);
        wolf.setPosX(wolfX);
        wolf.setPosY(wolfY);
        wolf.setRec(wolfRec);
        wolf.setPlace(0);
        sheep.setPosX(sheepX);
        sheep.setPosY(sheepY);
        sheep.setRec(sheepRec);
        sheep.setPlace(0);
        vegetables.setPosX(vegetablesX);
        vegetables.setPosY(vegetablesY);
        vegetables.setRec(vegetablesRec);
        vegetables.setPlace(0);
        raft.setPosX(raftX);
        raft.setPosY(raftY);
        raft.setRec(raftRec);
        raft.setPlace(0);
        raft.setPassengers(0);
        raft.passengerList.clear();
        left.leftRaft.clear();
        getInitialCrossers();
        right.rightRaft.clear();
        undo.clear();
        undo.push(new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata()));
        redo.clear();
    }

    public void setPositions(Momento undo) {
        raft.setPosX(undo.getRaftX());
        raft.setPosY(undo.getRaftY());
        farmer.setPosX(undo.getChar1X());
        farmer.setPosY(undo.getChar1Y());
        vegetables.setPosX(undo.getChar2X());
        vegetables.setPosY(undo.getChar2Y());
        wolf.setPosX(undo.getChar3X());
        wolf.setPosY(undo.getChar3Y());
        sheep.setPosX(undo.getChar4X());
        sheep.setPosY(undo.getChar4Y());
        left.setLeftRaft(undo.getLeft());
        right.setRightRaft(undo.getRight());
        raft.setPassengerList(getList(undo));
        raft.setPlace(undo.getRaftplace());
        farmer.setPlace(undo.getPlace1());
        raft.setMoves(undo.getMoves());
        wolf.setPlace(undo.getPlace2());
        sheep.setPlace(undo.getPlace3());
        vegetables.setPlace(undo.getPlace4());
        raft.setPassengers(undo.getPassengers());
    }

    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {

        if (!boatRiders.isEmpty()) {

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
        List<Integer> x = new ArrayList<>();
        x.add(1);
        x.add(2);
        x.add(3);
        x.add(4);
        left.leftRaft = x;
        return crossers;
    }

    @Override
    public String[] getInstructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAllRec() {
        Rectangle2D rec1 = new Rectangle2D(farmer.getPosX(), farmer.getPosY(), farmer.bi.getWidth(),
                farmer.bi.getHeight());
        farmer.setRec(rec1);
        Rectangle2D rec2 = new Rectangle2D(wolf.getPosX(), wolf.getPosY(), wolf.bi.getWidth(),
                wolf.bi.getHeight());
        wolf.setRec(rec2);
        Rectangle2D rec3 = new Rectangle2D(sheep.getPosX(), sheep.getPosY(), sheep.bi.getWidth(),
                sheep.bi.getHeight());
        sheep.setRec(rec3);
        Rectangle2D rec4 = new Rectangle2D(vegetables.getPosX(), vegetables.getPosY(), vegetables.bi.getWidth(),
                vegetables.bi.getHeight());
        vegetables.setRec(rec4);

    }

    public int[] momentodata() {
        int arr[] = new int[18];
        arr[0] = raft.getPosX();
        arr[1] = raft.getPosY();

        arr[2] = farmer.getPosX();
        arr[3] = farmer.getPosY();

        arr[4] = vegetables.getPosX();
        arr[5] = vegetables.getPosY();

        arr[6] = wolf.getPosX();
        arr[7] = wolf.getPosY();

        arr[8] = sheep.getPosX();
        arr[9] = sheep.getPosY();

        arr[10] = raft.getPlace();
        arr[11] = farmer.getPlace();

        arr[12] = wolf.getPlace();
        arr[13] = sheep.getPlace();
        arr[14] = vegetables.getPlace();
        arr[15] = 0;
        arr[16] = raft.getPassengers();
        arr[17] = raft.getMoves();
        return arr;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<ICrosser> getList(Momento m) {
        List<ICrosser> crosser = new ArrayList<>();
        if (!m.passengerList.contains(-1)) {
            Iterator<Integer> irr = m.passengerList.iterator();
            while (irr.hasNext()) {
                int x = irr.next();
                if (x == 1) {
                    crosser.add(farmer);
                } else if (x == 2) {
                    crosser.add(wolf);
                } else if (x == 3) {
                    crosser.add(sheep);
                } else if (x == 4) {
                    crosser.add(vegetables);
                }
            }
            return crosser;
        } else {
            m.passengerList.clear();
            return crosser;
        }

    }

    public List<ICrosser> getList(List<Integer> passengerList) {
        List<ICrosser> crosser = new ArrayList<>();
        if (!passengerList.contains(-1)) {
            Iterator<Integer> irr = passengerList.iterator();
            while (irr.hasNext()) {
                int x = irr.next();
                if (x == 1) {
                    crosser.add(farmer);
                } else if (x == 2) {
                    crosser.add(wolf);
                } else if (x == 3) {
                    crosser.add(sheep);
                } else if (x == 4) {
                    crosser.add(vegetables);
                }
            }
            return crosser;
        } else {
            passengerList.clear();
            return crosser;
        }
    }

}
