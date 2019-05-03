package command;

import command.Command;
import java.util.Stack;
import rivercross.ICrossingStrategy;
import rivercross.Momento;
import rivercross.Raft;

public class Undo implements Command {
    private ICrossingStrategy story;
    private Raft raft;
    private Stack<Momento> undo ;
    private Stack<Momento> redo ;
    @Override
    public void execute() {
        if (raft.getMoves() > 0) {
            raft.setMoves(raft.getMoves() - 1);
        }
        redo.push(undo.pop());
        story.setPositions(undo.peek());
        story.setAllRec();
    }

    public Undo(ICrossingStrategy story, Raft raft, Stack<Momento> undo, Stack<Momento> redo) {
        this.story = story;
        this.raft = raft;
        this.undo = undo;
        this.redo = redo;
    }

}
