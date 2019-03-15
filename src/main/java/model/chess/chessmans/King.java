package model.chess.chessmans;

import model.chess.chessboard.Position;

public class King extends Chessman {
    public King(ChessmanColor color, Position position) {
        super(color, position);
    }

    public King(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        return position.charDistance(dst) <= 1
                && position.numericDistance(dst) <= 1;
    }
}
