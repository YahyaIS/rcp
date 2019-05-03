
package command;


import rivercross.ICrossingStrategy;
import rivercross.Momento;
import rivercross.Raft;
import rivercross.Side;
import rivercross.Xml;

public class Save implements Command{
    private Raft raft;
    private Side left,right;
    private ICrossingStrategy story;
    private String fileName;
    @Override
    
    public void execute() {
        Xml x = new Xml(fileName);
                                try {
                                    x.createXml(new Momento(left.getLeftRaft(), right.getRightRaft(), raft.getPassengerList(), story.momentodata()));
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }
    }

    public Save(Raft raft, Side left, Side right, ICrossingStrategy story ,String fileName) {
        this.raft = raft;
        this.left = left;
        this.right = right;
        this.story = story;
        this.fileName = fileName;
    }


    
}
