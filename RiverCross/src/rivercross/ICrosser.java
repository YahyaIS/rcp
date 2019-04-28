/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rivercross;

import java.awt.image.BufferedImage;
import javafx.geometry.Rectangle2D;
/**
 *
 * @author seif
 */
public interface ICrosser {
    /**
     * @return whether the crosser can sail the boat or not
     */
    public boolean canSail();
    /**
     * @return get the weight of the crosser
     */
    public double getWeight();
    /**
     * @return get the eating rank of the crosser
     * this rank can be used to detect if one
     * crosser can eat/harm another crosser
     */
    public int getEatingRank();
    /**
     *
     * @return images of the crosser
     * each crosser must have at least two images, each one
     * is used on one bank of the river
     */
    public BufferedImage getImages();
    /**
     * @return exact copy of the crosser
     */
    public ICrosser makeCopy();
    /**
     * this field is used by the game strategy to set the label which
     * will be shown beside the crosser in the game view
     * to inform the user about the criteria of the current level
     * e.g. crosser eating rank
     * @param label
     */
    public void setLabelToBeShown(String label);
    /**
     * @return gets label of the crosser depending on the current game
     */
    public String getLabelToBeShown();

public int getPosX();

    public void setPosX(int posX) ;

    public int getPosY();

    public void setPosY(int posY);

    public void setRec(Rectangle2D rec);

    public Rectangle2D getRec() ;

    public int getPlace() ;

    public void setPlace(int place);
    public int getId();

}