package model.chess.moveCheckers;

import model.chess.Move;
import model.chess.chessboard.Chessboard;
import model.chess.chessboard.Position;
import model.chess.chessmans.Chessman;
import model.chess.exceptions.ChessException;

import java.util.Objects;

public class DestinationChecker extends MoveChecker {
    private Chessboard board;

    public DestinationChecker(Chessboard board) {
        this.board = board;
    }

    @Override
    protected void validate(Move move) throws ChessException {
        Chessman srcFigure = board.get(move.getSrcPosition());
        Position dstPosition = move.getDstPosition();
        Chessman dstFigure = board.get(dstPosition);
        if (Objects.isNull(dstFigure) && !srcFigure.canReach(dstPosition)) {
            throw new ChessException("Invalid move");
        } else if (Objects.nonNull(dstFigure) && !srcFigure.canCapture(dstPosition)) {
            throw new ChessException("Invalid move");
        }
    }
}
