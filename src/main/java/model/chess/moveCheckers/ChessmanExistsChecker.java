package model.chess.moveCheckers;

import model.chess.Move;
import model.chess.chessboard.Chessboard;
import model.chess.exceptions.ChessException;

import java.util.Objects;

public class ChessmanExistsChecker extends MoveChecker {
    private Chessboard board;

    public ChessmanExistsChecker(Chessboard board) {
        this.board = board;
    }

    @Override
    protected void validate(Move move) throws ChessException {
        if (Objects.isNull(board.get(move.getSrcPosition()))) {
            throw new ChessException("There is no any chessman at " + move.getSrcPosition());
        }
    }
}
