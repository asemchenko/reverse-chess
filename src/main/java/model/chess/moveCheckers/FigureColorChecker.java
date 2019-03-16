package model.chess.moveCheckers;

import model.chess.Move;
import model.chess.chessboard.Chessboard;
import model.chess.chessmans.Chessman;
import model.chess.chessmans.ChessmanColor;
import model.chess.exceptions.ChessException;

import java.util.function.Supplier;

public class FigureColorChecker extends MoveChecker {
    private Supplier<ChessmanColor> rightColorSupplier;
    private Chessboard board;

    public FigureColorChecker(Supplier<ChessmanColor> rightColorSupplier, Chessboard board) {
        this.rightColorSupplier = rightColorSupplier;
        this.board = board;
    }

    @Override
    public void validate(Move move) throws ChessException {
        Chessman c = board.get(move.getSrcPosition());
        if (!c.getColor().equals(getRightColor())) {
            throw new ChessException(String.format("%s is going now", getRightColor()));
        }
    }

    private ChessmanColor getRightColor() {
        return rightColorSupplier.get();
    }
}
