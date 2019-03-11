package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/* TODO
 *  проверь canBeMoved всех фигур на правильность обработки хода,
 *  при котором фигура не перемещается
 */
public class Rook extends Chessboard.Chessman {
    public Rook(@NotNull Chessboard board, @NotNull ChessmanColor color, @NotNull Position position) {
        board.super(color, position);
    }

    @Override
    public boolean canBeMoved(Position dst) {
        return checkForLegalMove(dst)
                && checkForFreeRoute(dst)
                && checkForRightEnemy(dst);
    }

    /**
     * Checks that <code>dst</code> is accessible from current position
     * according to chessman moving rules.
     * <b>Does not check that every cell of
     * route is not occupied.</b>
     *
     * @param dst destination position
     * @return true if <code>dst</code> is accessible
     */
    public boolean checkForLegalMove(Position dst) {
        int charD = position.charDistance(dst);
        int numD = position.numericDistance(dst);
        // or horizonatal or vertical move
        return (charD == 0 && numD > 0)
                || (charD > 0 && numD == 0);
    }

    public boolean checkForFreeRoute(Position dst) {
        int charOffset = Integer.signum(dst.charSubstract(position));
        int numOffset = Integer.signum(dst.numericSubstract(position));
        Position p = new Position(position);
        p.move(charOffset, numOffset);
        for (; !p.equals(dst); p.move(charOffset, numOffset)) {
            if (get(p) != null) return false;
        }
        return true;
    }
}
