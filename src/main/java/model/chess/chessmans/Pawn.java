package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

// TODO как реализовать изменение типа фигуры, когда пешка достигает конца поля???
public class Pawn extends Chessboard.Chessman {
    public Pawn(@NotNull Chessboard board, @NotNull ChessmanColor color, @NotNull Position position) {
        board.super(color, position);
    }

    @Override
    public boolean canBeMoved(@NotNull Position dst) {
        return checkInitialBigMove(dst)
                || checkCapture(dst)
                || checkSimpleMove(dst);
    }

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

    private boolean checkCapture(@NotNull Position dst) {
        return checkCaptureWhite(dst) || checkCaptureBlack(dst);
    }

    private boolean checkCaptureWhite(@NotNull Position dst) {
        return color.equals(ChessmanColor.WHITE)
                && Objects.nonNull(get(dst))
                && dst.numericDistance(position) == 1
                && position.charDistance(dst) == 1;
    }

    private boolean checkCaptureBlack(@NotNull Position dst) {
        return color.equals(ChessmanColor.BLACK)
                && Objects.nonNull(get(dst))
                && dst.numericDistance(position) == -1
                && position.charDistance(dst) == 1;
    }

    private boolean checkSimpleMove(@NotNull Position dst) {
        return checkSimpleMoveWhite(dst) || checkSimpleMoveBlack(dst);
    }

    private boolean checkSimpleMoveWhite(@NotNull Position dst) {
        return color.equals(ChessmanColor.WHITE)
                && dst.numericDistance(position) == 1
                && dst.charDistance(position) == 0;
    }

    private boolean checkSimpleMoveBlack(@NotNull Position dst) {
        return color.equals(ChessmanColor.BLACK)
                && dst.numericDistance(position) == -1
                && dst.charDistance(position) == 0;
    }
}
