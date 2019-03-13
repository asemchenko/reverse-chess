package model.chess.chessmans;

import model.chess.ChessmanColor;
import model.chess.Position;

import java.util.Iterator;

public class Knight extends Chessman {
    public Knight(ChessmanColor color, Position position) {
        super(color, position);
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
        int charDirection = dst.charSubstract(position);
        int numDirection = dst.numericSubstract(position);
        return new RouteIterator(position, dst, charDirection, numDirection);
    }
}
