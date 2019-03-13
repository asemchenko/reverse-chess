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
    // Most common behaviour. Should be override if needed
    protected Iterator<Position> route(Position dst) {
        int charDirection = Integer.signum(dst.charSubstract(position));
        int numDirection = Integer.signum(dst.numericSubstract(position));
        return new RouteIterator(position, dst, charDirection, numDirection);
    }

    protected class RouteIterator implements Iterator<Position> {
        private Position src;
        private Position dst;
        private int charD;
        private int numD;

        public RouteIterator(Position src, Position dst, int charD, int numD) {
            this.src = new Position(src);
            this.dst = dst;
            this.charD = charD;
            this.numD = numD;
        }

        @Override
        public boolean hasNext() {
            // FIXME как то не оч создавать новый объект position постоянно
            var nPos = new Position(src);
            nPos.move(charD, numD);
            return !nPos.equals(dst);
        }

        @Override
        public Position next() {
            src.move(charD, numD);
            return new Position(src);
        }
    }

}
