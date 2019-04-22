package rivercross;

import java.util.ArrayList;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Level1 implements ICrossingStrategy {
    
    private Side left = new Side(1);
    private Side right = new Side(2);
    private Raft raft = new Raft();
    private Vegetables vegetables= new Vegetables();
    private Human farmer = new Human();
    private Wolf wolf = new Wolf();
    private Sheep sheep = new Sheep();
    
    
    public void draw(GraphicsContext gc, BackGround bg,List<Water> waterpic) {
        bg.drawBackground(gc, waterpic);
        gc.drawImage(left.getImage(), left.getxPos(), left.getYPos());
        gc.drawImage(right.getImage(), right.getxPos(), right.getYPos());
        gc.drawImage(raft.getImage(), raft.getPosX(), raft.getPosY());
        gc.drawImage(farmer.getImage(), farmer.getPosX(), farmer.getPosY());
        gc.drawImage(wolf.getImage(), wolf.getPosX(), wolf.getPosY());
        gc.drawImage(sheep.getImage(), sheep.getPosX(), sheep.getPosY());
        gc.drawImage(vegetables.getImage(), vegetables.getPosX(), vegetables.getPosY());
        gc.drawImage(raft.getMoveImage(), 448, 50);
        
    }

    public void moves(Scene theScene) {
        theScene.setOnMouseClicked(
                (EventHandler<MouseEvent>) e -> {
                    if (raft.getMoveRec().contains(e.getX(), e.getY())) {
                        
                        raft.move(e);
                    } else if (farmer.getRec().contains(e.getX(), e.getY())) {
                        farmer.move(e, raft);
                    } else if (wolf.getRec().contains(e.getX(), e.getY())) {
                        wolf.move(e, raft);
                    } else if (sheep.getRec().contains(e.getX(), e.getY())) {
                        sheep.move(e, raft);
                    } else if (vegetables.getRec().contains(e.getX(), e.getY())) {
                        vegetables.move(e, raft);

                    }

                });
    }

    

    @Override
    public String[] getInstructions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isValid(List<ICrosser> rightBankCrossers, List<ICrosser> leftBankCrossers, List<ICrosser> boatRiders) {
        return true;
    }

    @Override
    public List<ICrosser> getInitialCrossers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setRaft(Raft raft) {
        this.raft = raft;
    }

    public void setVegetables(Vegetables vegetables) {
        this.vegetables = vegetables;
    }

    public void setFarmer(Human farmer) {
        this.farmer = farmer;
    }

    public void setWolf(Wolf wolf) {
        this.wolf = wolf;
    }

    public void setSheep(Sheep sheep) {
        this.sheep = sheep;
    }

    
    
}
