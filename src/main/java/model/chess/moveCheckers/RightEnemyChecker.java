package model.chess.moveCheckers;

import model.chess.chessboard.Chessboard;
import model.chess.Move;
import model.chess.chessmans.Chessman;
import model.chess.exceptions.ChessException;

import java.util.Objects;

public class RightEnemyChecker extends MoveChecker {
    private Chessboard board;

    public RightEnemyChecker(Chessboard board) {
        this.board = board;
    }

    @Override
    public void validate(Move move) throws ChessException {
        Chessman c = board.get(move.getSrcPosition());
        Chessman enemy = board.get(move.getDstPosition());
        if (Objects.nonNull(enemy) && enemy.getColor().equals(c.getColor())) {
            throw new ChessException("Attempt to capture own chessman during " + move);
        }
    }
}
