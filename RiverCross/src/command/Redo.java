package command;

import command.Command;
import java.util.Stack;
import rivercross.ICrossingStrategy;
import rivercross.Momento;
import rivercross.Raft;

public class Redo implements Command {
    private Raft raft;
    private Stack<Momento> undo ;
    private Stack<Momento> redo ;
    private ICrossingStrategy story;
    @Override
    public void execute() {
        raft.setMoves(raft.getMoves() + 1);
        story.setPositions(redo.peek());
        undo.push(redo.pop());
        story.setAllRec();
    }

    public Redo(Raft raft, Stack<Momento> undo, Stack<Momento> redo, ICrossingStrategy story) {
        this.raft = raft;
        this.undo = undo;
        this.redo = redo;
        this.story = story;
    }

}
