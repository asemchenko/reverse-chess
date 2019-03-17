package model.chess.chessmans;

import com.google.common.collect.Iterables;
import model.chess.chessboard.Position;

import java.util.Iterator;

public class Knight extends Chessman {
    public Knight(ChessmanColor color, Position position) {
        super(color, position);
    }

    public Knight(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        int charD = position.charDistance(dst);
        int numD = position.numericDistance(dst);
        return (charD == 1 && numD == 2)
                || (charD == 2 && numD == 1);
    }

    @Override
    protected Iterator<Position> route(Position dst) {
        int charDirection = dst.charDiff(position);
        int numDirection = dst.numericDiff(position);
        return new RouteIterator(position, dst, charDirection, numDirection);
    }

    @Override
    public Iterable<Position> getUnderAttack() {
        // TODO стремный код, отрефакторь обязательно, такое же и в остальных классах
        return Iterables.concat(
                () -> new RouteIterator(getPosition(), 1, 2),
                () -> new RouteIterator(getPosition(), -1, 2),

                () -> new RouteIterator(getPosition(), 2, 1),
                () -> new RouteIterator(getPosition(), 2, -1),

                () -> new RouteIterator(getPosition(), -2, 1),
                () -> new RouteIterator(getPosition(), -2, -1),

                () -> new RouteIterator(getPosition(), 1, -2),
                () -> new RouteIterator(getPosition(), -1, -2)
        );
    }

    @Override
    protected char getCharRepresentationIgnoreColor() {
        return 'n';
    }
}
