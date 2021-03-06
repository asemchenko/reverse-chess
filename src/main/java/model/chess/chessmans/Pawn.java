package model.chess.chessmans;

import model.chess.chessboard.Position;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

// TODO как реализовать изменение типа фигуры, когда пешка достигает конца поля???
public class Pawn extends Chessman {
    public Pawn(ChessmanColor color, Position position) {
        super(color, position);
    }

    public Pawn(ChessmanColor color) {
        super(color);
    }

    @Override
    protected boolean isReachable(Position dst) {
        return checkInitialBigMove(dst)
                || checkSimpleMove(dst);
    }

    @Override
    public boolean canCapture(Position dst) {
        return canCaptureWhite(dst) || canCaptureBlack(dst);
    }

    private boolean canCaptureWhite(@NotNull Position dst) {
        return getColor().equals(ChessmanColor.WHITE)
                && dst.numericDiff(position) == 1
                && dst.charDistance(position) == 1;
    }

    private boolean canCaptureBlack(@NotNull Position dst) {
        return getColor().equals(ChessmanColor.BLACK)
                && dst.numericDiff(position) == -1
                && dst.charDistance(position) == 1;
    }

    // FIXME возможно есть способ уменьшить кол-во кода
    private boolean checkInitialBigMove(@NotNull Position dst) {
        return checkInitialBigMoveBlack(dst) || checkInitialBigMoveWhite(dst);
    }

    private boolean checkInitialBigMoveWhite(@NotNull Position dst) {
        if (color != ChessmanColor.WHITE) return false;
        if (position.getNumericPosition() != 2) return false;
        return position.getCharPosition() == dst.getCharPosition() && dst.getNumericPosition() == 4;
    }

    private boolean checkInitialBigMoveBlack(@NotNull Position dst) {
        if (color != ChessmanColor.BLACK) return false;
        if (position.getNumericPosition() != 7) return false;
        return position.getCharPosition() == dst.getCharPosition() && dst.getNumericPosition() == 5;
    }

    private boolean checkSimpleMove(@NotNull Position dst) {
        return checkSimpleMoveWhite(dst) || checkSimpleMoveBlack(dst);
    }

    private boolean checkSimpleMoveWhite(@NotNull Position dst) {
        return color.equals(ChessmanColor.WHITE)
                && dst.numericDiff(position) == 1
                && dst.charDiff(position) == 0;
    }

    private boolean checkSimpleMoveBlack(@NotNull Position dst) {
        return color.equals(ChessmanColor.BLACK)
                && dst.numericDiff(position) == -1
                && dst.charDiff(position) == 0;
    }

    @Override
    protected char getCharRepresentationIgnoreColor() {
        return 'p';
    }

    @Override
    public Iterable<Position> getUnderAttack() {
        ArrayList<Position> p = new ArrayList<Position>();
        int numD = (getColor().equals(ChessmanColor.WHITE) ? 1 : -1);
        if (position.canBeMoved(1, numD)) {
            p.add(position.move(1, numD));
        }
        if (position.canBeMoved(-1, numD)) {
            p.add(position.move(-1, numD));
        }
        return p;
    }
}
