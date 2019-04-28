/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class StoryTwo implements ICrossingStrategy {
    private boolean h=true;
    Xml xm=new Xml("Save.xml");

    private Options options = new Options();
    private Side left = new Side(1);
    private Side right = new Side(2);
    private List<Water> waterpic = new ArrayList<>();
    private Raft raft = new Raft();
    private Farmer1 farmer1 = new Farmer1(1);
    private Farmer1 farmer2 = new Farmer1(2);
    private Farmer1 farmer3 = new Farmer1(3);
    private Farmer1 farmer4 = new Farmer1(4);
    private Cat cat = new Cat();
    private Human human = new Human();
    private Momento m = new Momento();
    private Momento momento;
    private boolean x = false;
    private Stack<Momento> undo = new Stack<>();
    private Stack<Momento> redo = new Stack<>();
    private Menu menu;
    private int farmer1X,farmer1Y,farmer2X,farmer2Y,farmer3X,farmer3Y,farmer4X,farmer4Y,catX,catY,raftX,raftY;
    private Rectangle2D farmer1R,farmer2R,farmer3R,farmer4R,catR,raftR;
    public void draw(Scene scene, GraphicsContext gc, BackGround bg) {
        getInitialCrossers();
        for (int i = 0; i < 7; i++) {
            Water w = new Water(i * 179, 480);
            waterpic.add(w);
        }

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
                gc.drawImage(options.getSaveImage(),options.getSavePosX(),options.getSavePosY());
                gc.drawImage(options.getImage(), options.getBackPosX(), options.getBackPosY());
                gc.drawImage(options.getRestartImage(), options.getRestartPosX(), options.getRestartPosY());
                scene.setOnMouseClicked(
                        (EventHandler<MouseEvent>) e -> {
                            if (raft.getMoveRec().contains(e.getX(), e.getY())) {
                                if (raft.getWeightsum() <= 100 && isValid(null, null, raft.passengerList)) {
                                    if (x == false) {
                                        momento = new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata());
                                        undo.add(momento);
                                        x = true;
                                    }
                                    raft.move(e);
                                    momento = new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata());
                                    undo.add(momento);
                                }
                            } else if (farmer1.getRec().contains(e.getX(), e.getY())) {
                                farmer1.move(e, raft);
                            } else if (farmer2.getRec().contains(e.getX(), e.getY())) {
                                farmer2.move(e, raft);
                            } else if (farmer3.getRec().contains(e.getX(), e.getY())) {
                                farmer3.move(e, raft);
                            } else if (farmer4.getRec().contains(e.getX(), e.getY())) {
                                farmer4.move(e, raft);

                            } else if (cat.getRec().contains(e.getX(), e.getY())) {
                                cat.move(e, raft);
                            } else if (m.getUndorec().contains(e.getX(), e.getY()) && !undo.isEmpty() && undo.size() != 1) {
                                redo.push(undo.pop());
                                setPositions(undo.peek());
                                setAllRec();
                            } else if (m.getRedorec().contains(e.getX(), e.getY()) && !redo.isEmpty()) {
                                setPositions(redo.peek());
                                undo.push(redo.pop());
                                setAllRec();
                            } else if (options.getBackRec().contains(e.getX(), e.getY())) {
                                initPos();
                                menu.draw(scene, gc);
                            } else if (options.getRestartRec().contains(e.getX(), e.getY())) {
                                 initPos();
                            }
                            else if(options.getSaveRec().contains(e.getX(),e.getY())){
                                Xml x=new Xml("Level2.xml");
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
    
    public void initPos()
    {
        if(h==true)
        {
            farmer1X=farmer1.getPosX();
            farmer1Y=farmer1.getPosY();
            farmer1R=farmer1.getRec();
            farmer2X=farmer2.getPosX();
            farmer2Y=farmer2.getPosY();
            farmer2R=farmer2.getRec();
            farmer3X=farmer3.getPosX();
            farmer3Y=farmer3.getPosY();
            farmer3R=farmer3.getRec();
            farmer4X=farmer4.getPosX();
            farmer4Y=farmer4.getPosY();
            farmer4R=farmer4.getRec();
            catX=cat.getPosX();
            catY=cat.getPosY();
            catR=cat.getRec();
            raftX=raft.getPosX();
            raftY=raft.getPosY();
            raftR=raft.getRec();
            h=false;
        }
        farmer1.setPosX(farmer1X);
        farmer1.setPosY(farmer1Y);
        farmer1.setRec(farmer1R);
        farmer1.setPlace(0);
        farmer2.setPosX(farmer2X);
        farmer2.setPosY(farmer2Y);
        farmer2.setRec(farmer2R);
        farmer2.setPlace(0);
        farmer3.setPosX(farmer3X);
        farmer3.setPosY(farmer3Y);
        farmer3.setRec(farmer3R);
        farmer3.setPlace(0);
        farmer4.setPosX(farmer4X);
        farmer4.setPosY(farmer4Y);
        farmer4.setRec(farmer4R);
        farmer4.setPlace(0);
        cat.setPosX(catX);
        cat.setPosY(catY);
        cat.setRec(catR);
        cat.setPlace(0);
        raft.setPosX(raftX);
        raft.setPosY(raftY);
        raft.setRec(raftR);
        raft.setPlace(0);
        raft.setPassengers(0);
        raft.setWeightsum(0);
        raft.passengerList.clear();
        left.leftRaft.clear();
        getInitialCrossers();
        right.rightRaft.clear();
        undo.clear();
        undo.push(new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), momentodata()));
        redo.clear();
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
        return crossers;
    }

    @Override
    public String[] getInstructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAllRec() {
        Rectangle2D rec1 = new Rectangle2D(farmer1.getPosX(), farmer1.getPosY(), farmer1.bi.getWidth(),
                farmer1.bi.getHeight());
        farmer1.setRec(rec1);
        Rectangle2D rec2 = new Rectangle2D(farmer2.getPosX(), farmer2.getPosY(), farmer2.bi.getWidth(),
                farmer2.bi.getHeight());
        farmer2.setRec(rec2);
        Rectangle2D rec3 = new Rectangle2D(farmer3.getPosX(), farmer3.getPosY(), farmer3.bi.getWidth(),
                farmer3.bi.getHeight());
        farmer3.setRec(rec3);
        Rectangle2D rec4 = new Rectangle2D(farmer4.getPosX(), farmer4.getPosY(), farmer4.bi.getWidth(),
                farmer4.bi.getHeight());
        farmer4.setRec(rec4);
        Rectangle2D rec5 = new Rectangle2D(cat.getPosX(), cat.getPosY(), cat.bi.getWidth(),
                cat.bi.getHeight());
        cat.setRec(rec4);
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

    public void setPositions(Momento m) {
        raft.setPosX(m.getRaftX());
        raft.setPosY(m.getRaftY());
        farmer1.setPosX(m.getChar1X());
        farmer1.setPosY(m.getChar1Y());
        farmer2.setPosX(m.getChar2X());
        farmer2.setPosY(m.getChar2Y());
        farmer3.setPosX(m.getChar3X());
        farmer3.setPosY(m.getChar3Y());
        farmer4.setPosX(m.getChar4X());
        farmer4.setPosY(m.getChar4Y());
        raft.setPassengerList(getList(m));
        raft.setPlace(m.getRaftplace());

        farmer1.setPlace(m.getPlace1());
        farmer2.setPlace(m.getPlace2());
        farmer3.setPlace(m.getPlace3());
        farmer4.setPlace(m.getPlace4());

        cat.setPosX(m.getCatX());
        cat.setPosY(m.getCatY());
        cat.setPlace(m.getCatPlace());
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public List<ICrosser> getList(Momento m){
        List<ICrosser> crosser=new ArrayList<>();
        Iterator<Integer>irr=m.passengerList.iterator();
        while (irr.hasNext()){
            int x=irr.next();
            if(x==1){
                crosser.add(farmer1);
            }
            else  if(x==2){
                crosser.add(farmer2);
            }
            else if(x==3){
                crosser.add(farmer3);
            }
            else if(x==5){
                crosser.add(cat);
            }
            else {
                crosser.add(farmer4);
            }
        }
        return crosser;

    }

}
