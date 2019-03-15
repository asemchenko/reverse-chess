package model.chess.moveCheckers;

import model.chess.Move;
import model.chess.exceptions.ChessException;

import java.util.Objects;

public abstract class MoveChecker {
    private MoveChecker successor;

    public MoveChecker() {
    }

    public MoveChecker(MoveChecker successor) {
        this.successor = successor;
    }

    protected final void nextCheck(Move move) throws ChessException {
        if (Objects.nonNull(successor)) {
            successor.check(move);
        }
    }

    /*
        Returns successor for improve chain creating
        For example,
        new ConcreteChecker()
            .setSuccessor(new OtherConcreteChecker())
            .setSuccessor(...)
     */
    public final MoveChecker setSuccessor(MoveChecker checker) {
        successor = checker;
        return successor;
    }

    public final void check(Move move) throws ChessException {
        validate(move);
        nextCheck(move);
    }

    protected abstract void validate(Move move) throws ChessException;
}
