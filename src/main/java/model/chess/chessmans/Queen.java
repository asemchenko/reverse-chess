package model.chess.chessmans;

import com.google.common.collect.Iterables;
import model.chess.chessboard.Position;

public class Queen extends Chessman {
    public Queen(ChessmanColor color, Position position) {
        super(color, position);
    }

    public Queen(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        return new Rook(getColor(), getPosition()).isReachable(dst)
                || new Bishop(getColor(), getPosition()).isReachable(dst);
    }

    @Override
    public Iterable<Position> getUnderAttack() {
        return Iterables.concat(
                new Rook(getColor(), getPosition()).getUnderAttack(),
                new Bishop(getColor(), getPosition()).getUnderAttack()
        );
    }

    @Override
    protected char getCharRepresentationIgnoreColor() {
        return 'q';
    }
}
