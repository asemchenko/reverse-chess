package model.chess.chessmans;

import model.chess.chessboard.Position;
import model.chess.exceptions.ChessException;
import org.jetbrains.annotations.Nullable;

import javax.print.DocFlavor;
import java.util.Iterator;

public abstract class Chessman {
    protected ChessmanColor color;
    protected Position position;

    public Chessman(ChessmanColor color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Chessman(ChessmanColor color) {
        this.color = color;
    }

    public final Position getPosition() {
        return position;
    }

    public final void setPosition(Position position) {
        this.position = position;
    }

    public final ChessmanColor getColor() {
        return color;
    }

    @Nullable
    public final Iterable<Position> getRouteTo(Position dst) throws ChessException {
        // FIXME как лучше назвать этот метод? Он возвращает путь, исключая крайние точки
        if (!canBeMovedTo(dst)) {
            throw new ChessException(position + " can not be moved to " + dst);
        }
        return () -> route(dst);
    }

    public final boolean canBeMovedTo(Position dst) {
        if (position.equals(dst)) return false;
        return isReachable(dst);
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
            var nPos = new Position(src);
            if (Position.canBeMoved(nPos, charD, numD)) {
                nPos = Position.getMoved(nPos, charD, numD);
            } else {
                return false;
            }
            return !nPos.equals(dst);
        }

        @Override
        public Position next() {
            src = Position.getMoved(src, charD, numD);
            return new Position(src);
        }
    }

    public final char toChar() {
        char c = getCharRepresentationIgnoreColor();
        if (color.equals(ChessmanColor.WHITE)) {
            return Character.toUpperCase(c);
        }
        return Character.toLowerCase(c);
    }

    protected abstract char getCharRepresentationIgnoreColor();

}
