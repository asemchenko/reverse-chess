package model.chess;

import org.jetbrains.annotations.NotNull;

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
}
