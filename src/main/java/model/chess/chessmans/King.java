package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

public class King extends Chessboard.Chessman {
    public King(@NotNull Chessboard board, @NotNull ChessmanColor color, @NotNull Position position) {
        board.super(color, position);
    }

    @Override
    public boolean canBeMoved(Position dst) {
        return position.charDistance(dst) == 1
                || position.numericDistance(dst) == 1;
    }
}
