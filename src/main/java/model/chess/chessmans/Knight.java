package model.chess.chessmans;

import model.chess.Chessboard;
import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.NotNull;

public class Knight extends Chessboard.Chessman {
    public Knight(@NotNull Chessboard board, @NotNull ChessmanColor color, @NotNull Position position) {
        board.super(color, position);
    }

    @Override
    public boolean canBeMoved(Position dst) {
        int charD = position.charDistance(dst);
        int numD = position.numericDistance(dst);
        return (charD == 1 && numD == 2)
                || (charD == 2 && numD == 1);
    }
}
