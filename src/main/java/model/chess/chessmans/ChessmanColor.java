package model.chess.chessmans;

import org.jetbrains.annotations.Contract;

public enum ChessmanColor {
    BLACK, WHITE;

    @Contract(pure = true)
    public ChessmanColor getOpposite() {
        if (this == BLACK) return WHITE;
        return BLACK;
    }


}