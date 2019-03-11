package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

public class Queen extends Chessboard.Chessman {
    public Queen(@NotNull Chessboard board, @NotNull ChessmanColor color, @NotNull Position position) {
        board.super(color, position);
    }

    @Override
    public boolean canBeMoved(Position dst) {
        return canBeMovedAsBishop(dst) || canBeMovedAsRook(dst);
    }

    /* TODO
     *  подумай как убрать дубликаты методов checkFor(Rook|Bishop)(LegalMove|FreeRoute)
     *  их реализация взята из классов Rook и Bishop
     */
    public boolean canBeMovedAsRook(Position dst) {
        return checkForRookLegalMove(dst)
                && checkForRookFreeRoute(dst)
                && checkForRightEnemy(dst);
    }

    public boolean checkForRookLegalMove(Position dst) {
        int charD = position.charDistance(dst);
        int numD = position.numericDistance(dst);
        // or horizonatal or vertical move
        return (charD == 0 && numD > 0)
                || (charD > 0 && numD == 0);
    }

    public boolean checkForRookFreeRoute(Position dst) {
        int charOffset = Integer.signum(dst.charSubstract(position));
        int numOffset = Integer.signum(dst.numericSubstract(position));
        Position p = new Position(position);
        p.move(charOffset, numOffset);
        for (; !p.equals(dst); p.move(charOffset, numOffset)) {
            if (get(p) != null) return false;
        }
        return true;
    }

    public boolean canBeMovedAsBishop(Position dst) {
        return checkForBishopLegalMove(dst)
                && checkForBishopFreeRoute(dst)
                && checkForRightEnemy(dst);
    }

    private boolean checkForBishopLegalMove(@NotNull Position dst) {
        return position.numericDistance(dst) == position.charDistance(dst);
    }

    private boolean checkForBishopFreeRoute(@NotNull Position dst) {
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