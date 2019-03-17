package model.chess.chessmans;

import com.google.common.collect.Iterables;
import model.chess.chessboard.Position;


public class Rook extends Chessman {
    public Rook(ChessmanColor color, Position position) {
        super(color, position);
    }

    public Rook(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        int charD = position.charDistance(dst);
        int numD = position.numericDistance(dst);
        // or horizontal or vertical move
        return (charD == 0 && numD > 0)
                || (charD > 0 && numD == 0);
    }

    @Override
    public Iterable<Position> getUnderAttack() {
        return Iterables.concat(
                () -> new RouteIterator(getPosition(),0, 1),
                () -> new RouteIterator(getPosition(),0, -1),
                () -> new RouteIterator(getPosition(),-1, 0),
                () -> new RouteIterator(getPosition(),1, 0)
        );
    }

    @Override
    protected char getCharRepresentationIgnoreColor() {
        return 'r';
    }
}
