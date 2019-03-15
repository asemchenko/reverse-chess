package model.chess.chessmans;

import model.chess.chessboard.Position;
import org.jetbrains.annotations.NotNull;

public class Bishop extends Chessman {
    public Bishop(ChessmanColor color, Position position) {
        super(color, position);
    }

    public Bishop(ChessmanColor color) {
        super(color);
    }

    /**
     * Checks if <code>dst</code> is on the same diagonal as
     * this chessman
     *
     * @param dst destination of the move
     * @return the result of check
     */
    @Override
    protected boolean isReachable(@NotNull Position dst) {
        int numD = position.numericDistance(dst);
        int charD = position.charDistance(dst);
        return numD == charD;
    }
}
