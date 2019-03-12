package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

// TODO проверить все фигуры на возможность походить не изменяя своего положения
public class Bishop extends Chessboard.Chessman {
    public Bishop(@NotNull Chessboard board, @NotNull ChessmanColor color, @NotNull Position position) {
        board.super(color, position);
    }

    @Override
    public boolean canBeMoved(Position dst) {
        return checkForLegalMove(dst) && checkForFreeRoute(dst) && checkForRightEnemy(dst);
    }

    /**
     * Checks if <code>dst</code> is on the same diagonal as
     * this chessman
     *
     * @param dst destination of the move
     * @return the result of check
     */
    private boolean checkForLegalMove(@NotNull Position dst) {
        int numD = position.numericDistance(dst);
        int charD = position.charDistance(dst);
        return (numD == charD) && (numD != 0);
    }

    /**
     * Check if there is any chessman on move from current position
     * to <code>dst</code>.
     *
     * @param dst destination position
     * @return true if route is free, false otherwise
     */
    private boolean checkForFreeRoute(@NotNull Position dst) {
        int charDirection = Integer.signum(dst.charSubstract(position));
        int numDirection = Integer.signum(dst.numericSubstract(position));
        Position p = new Position(position);
        for (p.move(charDirection, numDirection); !p.equals(dst); p.move(charDirection, numDirection)) {
            if (get(p) != null) {
                return false;
            }
        }
        return true;
    }
}
