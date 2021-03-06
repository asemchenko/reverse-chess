package model.chess;

import model.chess.chessboard.Position;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Move {
    private Position srcPosition;
    private Position dstPosition;

    public Move(@NotNull Position srcPosition, @NotNull Position dstPosition) {
        this.srcPosition = srcPosition;
        this.dstPosition = dstPosition;
    }

    @NotNull
    public Position getSrcPosition() {
        return srcPosition;
    }

    public void setSrcPosition(@NotNull Position srcPosition) {
        this.srcPosition = srcPosition;
    }

    @NotNull
    public Position getDstPosition() {
        return dstPosition;
    }

    public void setDstPosition(@NotNull Position dstPosition) {
        this.dstPosition = dstPosition;
    }

    @Override
    public String toString() {
        return "Move{" + srcPosition + " -> " + dstPosition + " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(getSrcPosition(), move.getSrcPosition()) &&
                Objects.equals(getDstPosition(), move.getDstPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSrcPosition(), getDstPosition());
    }
}
