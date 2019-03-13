package model.chess.chessmans;

import model.chess.ChessmanColor;
import model.chess.Position;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public abstract class Chessman {
    protected ChessmanColor color;
    protected Position position;

    public Chessman(ChessmanColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public final Position getPosition() {
        return position;
    }

    public final ChessmanColor getColor() {
        return color;
    }

    @Nullable
    public final Iterable<Position> getRouteTo(Position dst) {
        // FIXME как лучше назвать этот метод? Он возвращает путь, исключая крайние точки
        if (!isReachable(dst)) {
            return null;
        }
        return () -> route(dst);
    }

    protected abstract boolean isReachable(Position dst);

    protected abstract Iterator<Position> route(Position dst);
}
