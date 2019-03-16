package model.chess.chessmans;

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
    protected char getCharRepresentationIgnoreColor() {
        return 'n';
    }
}
